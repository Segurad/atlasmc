package de.atlasmc.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;

import de.atlasmc.Atlas;
import de.atlasmc.atlasnetwork.server.LocalServer;
import de.atlasmc.atlasnetwork.server.ServerGroup;
import de.atlasmc.util.ConcurrentLinkedList;
import de.atlasmc.util.ConcurrentLinkedList.LinkedListIterator;
import de.atlasmc.util.annotation.NotNull;
import de.atlasmc.util.annotation.ThreadSafe;

/**
 * This class holds all {@link EventExecutor} for the Event it is used
 */
@ThreadSafe
public class HandlerList {
	
	protected static final List<HandlerList> HANDLERS = new ArrayList<>();
	private static int lowID;
	
	private final ConcurrentLinkedList<EventExecutor> globalExecutors;
	private final int handlerID;
	private EventExecutor defaultExecutor;
	
	public HandlerList() {
		this.defaultExecutor = EventExecutor.NULL_EXECUTOR;
		this.globalExecutors = new ConcurrentLinkedList<>();
		handlerID = registerHandlerList();
	}
	
	/**
	 * Sets the DefaultEventExecutor<br>
	 * <br>
	 * The DefaultEventExecutor is always the last EventExecutor called, ignoring the flags ({@link EventPriority} and ignoreCancelled)
	 * @param defaultExecutor
	 */
	public void setDefaultExecutor(EventExecutor defaultExecutor) {
		if (defaultExecutor == null) 
			defaultExecutor = EventExecutor.NULL_EXECUTOR;
		this.defaultExecutor = defaultExecutor;
	}
	
	/**
	 * Returns the DefaultEventExecutor of this HandlerList by Default it is the {@link EventExecutor#NULL_EXECUTOR}
	 * @return a EventExecutor
	 */
	public EventExecutor getDefaultExecutor() {
		return defaultExecutor;
	}
	
	/**
	 * Fires the Event for the Default event executor
	 * @param event
	 * @param log the logger error should be send to
	 */
	protected void fireDefaultExecutor(Event event, Logger log) {
		try {
			defaultExecutor.fireEvent(event);
		} catch (Exception ex) {
			log.error("Error while event handly with default handler for: " + event.getName(), ex);
		}
	}
	
	/**
	 * Registers a HandlerList and returns the ID
	 * @param handler
	 * @return the handler's ID
	 */
	private final int registerHandlerList() {
		synchronized (HANDLERS) {
			final int size = HANDLERS.size();
			int id = -1;
			if (lowID == size) {
				HANDLERS.add(lowID, this);
				id = lowID;
				lowID++;
			} else {
				HANDLERS.set(lowID, this);
				id = lowID;
				boolean result = false;
				for (int i = lowID; i < size; i++) {
					if (HANDLERS.get(i) != null) continue;
					lowID = i;
					result = true;
					break;
				}
				if (!result) lowID = size;
			}
			return id;
		}
	}
	
	/**
	 * Unregisters this HandlerList<br>
	 * Should only be called by System while unloading Events added by Plugins
	 */
	public void unregister() {
		synchronized (HANDLERS) {
			int id = this.getHandlerID();
			HANDLERS.set(id, null);
			if (id < lowID) lowID = id;
		}
	}
	
	public final int getHandlerID() {
		return handlerID;
	}
	
	public void registerExecutor(@NotNull EventExecutor executor) {
		if (executor == null) 
			return;
		register(globalExecutors, executor);
	}
	
	public void registerExecutor(EventExecutor executor, Object... handleroptions) {
		registerExecutor(executor);
	}
	
	/**
	 * Inserts a executor to the list and groups it with other executors of the same priority
	 * @param exes
	 * @param executor
	 */
	protected synchronized void register(ConcurrentLinkedList<EventExecutor> exes, EventExecutor executor) {
		LinkedListIterator<EventExecutor> it = exes.iterator();
		final int ordinal = executor.getPriority().ordinal();
		while(it.hasNext()) {
			EventExecutor exe = it.next();
			if (exe.getPriority().ordinal() > ordinal) {
				if (it.hasPrevious()) {
					it.previous();
					it.add(executor);
					return;
				}
				exes.addFirst(executor);
				return;
			}
		}
		exes.add(executor);
	}
	
	/**
	 * 
	 * @return a immutable copy of all registered GlobalExecutors
	 */
	public LinkedListIterator<EventExecutor> getExecutors() {
		return globalExecutors.iterator();
	}
	
	public static void callEvent(@NotNull final Event event) {
		final HandlerList hl = event.getHandlers();
		if (!event.isAsynchronous() && event instanceof GenericEvent) {
			GenericEvent<?, ?> gEvent = (GenericEvent<?, ?>) event;
			if (!gEvent.getSyncThreadHolder().isSync())
				throw new EventException("Tried to call sync event asynchronous!");
		}
		hl.callEvent(event, event instanceof Cancellable);
	}
	
	/**
	 * Calls all EventExecutors of this HandlerList.
	 * This Method will be called by the static Method {@link #callEvent(Event)} and should be used for children to fire Events
	 * @param event
	 * @param cancellable
	 */
	protected void callEvent(final Event event, boolean cancellable) {
		if (globalExecutors.isEmpty()) {
			getDefaultExecutor().fireEvent(event);
			return;
		}
		boolean defaultHandler = false;
		for (EventExecutor exe : globalExecutors){
			if (exe.getPriority() == EventPriority.MONITOR && !defaultHandler) {
				defaultHandler = true;
				fireDefaultExecutor(event, Atlas.getLogger());
			}
			try {
				exe.fireEvent(event);
			} catch (Exception ex) {
				Atlas.getLogger().error("Error while event handling for: " + event.getName(), ex);
			}
		}
	}
	
	/**
	 * Fires the Event for all Executors with a lower or equal priority
	 * @param executors
	 * @param priority
	 * @param event event
	 * @param log the logger all error should be send to
	 * @param cancellable whether or not the event extends {@link Cancellable}
	 */
	protected static void fireEvents(final LinkedListIterator<EventExecutor> executors, final EventPriority priority, final Event event, final boolean cancellable, Logger log) {
		if (executors == null || !executors.hasNext()) 
			return;
		final int prio = priority.ordinal();
		for (EventExecutor exe = executors.peekNext(); exe != null; exe = executors.peekNext()) {
			if (exe.getPriority().ordinal() > prio) 
				break;
			executors.gotoPeeked();
			if (exe.getIgnoreCancelled() && (!cancellable && ((Cancellable) event).isCancelled()))
				continue;
			try {
				exe.fireEvent(event);
			} catch (Exception ex) {
				log.error("Error while event handling for: " + event.getName(), ex);
			}
		}
	}
	
	/**
	 * Unregisters the Listener from all HandlerLists
	 * @param listener
	 */
	public static void unregisterListenerGlobal(@NotNull Listener listener) {
		synchronized (HANDLERS) {
			for (HandlerList h : HANDLERS) {
				h.unregisterListener(listener);
			}
		}
	}

	/**
	 * Unregisters the Listener from this HandlerList
	 * @param listener
	 */
	public synchronized void unregisterListener(Listener listener) {
		Iterator<EventExecutor> it = globalExecutors.iterator();
		while(it.hasNext()) {
			EventExecutor exe = it.next();
			if (exe.getListener() == listener) it.remove();
		}
	}
	
	/**
	 * If the Listener contains a e.g. a {@link AbstractServerEvent}, you may add a {@link LocalServer} or {@link ServerGroup} for registration.
	 * If there are multiple LocalServers or/and ServerGroups it will registered for all.
	 * If not present or does not contain a specific Object the EventHandler will be registered as Global.
	 * @param listener
	 * @param handleroptions extra arguments for EventExecutor registration <br>
	 */
	public static void registerListener(Listener listener, Object... handleroptions) {
		if (listener == null) return;
		List<EventExecutor> exes = MethodEventExecutor.getExecutors(listener);
		for (EventExecutor exe : exes) {
			HandlerList handlers = getHandlerListOf(exe.getEventClass());
			handlers.registerExecutor(exe, handleroptions);
		}
	}
	
	public static <E extends Event> void registerFunctionalListener(Class<E> eventClass, FunctionalListener<E> listener, Object... handleroptions) {
		HandlerList handlers = getHandlerListOf(eventClass);
		FunctionalListenerExecutor exe = new FunctionalListenerExecutor(eventClass, listener);
		handlers.registerExecutor(exe, handleroptions);
	}
	
	/**
	 * Returns the HandlerList of a event class
	 * @param eventClass
	 * @return handler list
	 */
	public static HandlerList getHandlerListOf(Class<? extends Event> eventClass) {
		Method m;
		try {
			m = eventClass.getMethod("getHandlerList");
		} catch (NoSuchMethodException e) {
			throw new EventException("Unable to find static getHandlerList method in: " + eventClass.getName());
		} catch (SecurityException e) {
			throw new EventException("Unable to access static getHandlerList method in: " + eventClass.getName());
		}
		HandlerList h = null;
		try {
			m.setAccessible(true);
			h = (HandlerList) m.invoke(null);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new EventException("Unable to call static getHandlerList method!", e);
		}
		if (h == null)
			throw new EventException("Event class does not return a HandlerList: " + eventClass.getName());
		return h;
	}

}
