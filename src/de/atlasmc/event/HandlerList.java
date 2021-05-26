package de.atlasmc.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import de.atlasmc.atlasnetwork.server.LocalServer;
import de.atlasmc.atlasnetwork.server.ServerGroup;
import de.atlasmc.util.annotation.NotNull;
import de.atlasmc.util.annotation.ThreadSafe;

/**
 * This class holds all {@link EventExecutor} for specific Events
 */
@ThreadSafe
public class HandlerList {
	
	protected static final List<HandlerList> HANDLERS = new ArrayList<>();
	private final Vector<EventExecutor> globalExecutors;
	
	public HandlerList() {
		this.globalExecutors = new Vector<>();
		synchronized (HANDLERS) {
			HANDLERS.add(this);
		}
	}
	
	public void registerExecutor(@NotNull EventExecutor executor) {
		if (executor == null) return;
		register(globalExecutors, executor);
	}
	
	public void registerExecutor(EventExecutor executor, Object... handleroptions) {
		registerExecutor(executor);
	}
	
	protected synchronized void register(List<EventExecutor> exes, EventExecutor executor) {
		ListIterator<EventExecutor> it = exes.listIterator();
		final int ordinal = executor.getPriority().ordinal();
		while(it.hasNext()) {
			EventExecutor exe = it.next();
			if (exe.getPriority().ordinal() > ordinal) {
				if (it.hasPrevious()) {
					it.previous();
					it.add(executor);
					return;
				}
				exes.add(0, executor);
			}
		}
		exes.add(executor);
	}
	
	/**
	 * 
	 * @return a immutable copy of all registered GlobalExecutors
	 */
	public List<EventExecutor> getExecutors() {
		return List.copyOf(globalExecutors);
	}
	
	public static void callEvent(@NotNull final Event event) {
		final HandlerList hl = event.getHandlers();
		final boolean cancelled = Cancellable.class.isInstance(event);
		hl.callEvent(event, cancelled);
	}
	
	protected void callEvent(final Event event, boolean cancellable) {
		for (EventExecutor exe : globalExecutors){
			if (exe.getIgnoreCancelled() && cancellable) continue;
			exe.fireEvent(event);
		}
	}
	
	/**
	 * Fires the Event for all Executors with a lower or equal priority
	 * @param executors
	 * @param priority
	 * @param event event
	 * @param cancellable weather or not the event extends {@link Cancellable}
	 * @param index the start index
	 * @return
	 */
	protected static int fireEvents(final List<EventExecutor> executors, final EventPriority priority, final Event event, final boolean cancellable, final int index) {
		if (index == -1) return -1;
		final int length = executors.size();
		final int prio = priority.ordinal();
		for (int i = index; i < length; i++) {
			EventExecutor exe = executors.get(i);
			if (exe.getPriority().ordinal() > prio) return i;
			if (exe.getIgnoreCancelled() && (cancellable ? false : ((Cancellable) event).isCancelled())) continue;
			exe.fireEvent(event);
		}
		return -1;
	}
	
	public static void unregisterListener(@NotNull Listener listener) {
		synchronized (HANDLERS) {
			for (HandlerList h : HANDLERS) {
				h.unregisterHandledListener(listener);
			}
		}
	}

	public synchronized void unregisterHandledListener(Listener listener) {
		Iterator<EventExecutor> it = globalExecutors.iterator();
		while(it.hasNext()) {
			EventExecutor exe = it.next();
			if (exe.getListener() == listener) it.remove();
		}
	}
	
	/**
	 * 
	 * @param listener
	 * @param handleroptions extra arguments for EventExecutor registration <br>
	 * If the Listener contains a e.g. a {@link ServerEvent}, you may add a {@link LocalServer} or {@link ServerGroup} for registration.
	 * If there are multiple LocalServers or/and ServerGroups it will registered for all.
	 * If not present or does not contain a specific Object the EventHandler will be registered as Global.
	 */
	public static void registerListener(Listener listener, Object... handleroptions) {
		if (listener == null) return;
		List<EventExecutor> exes = getExecutors(listener);
		for (EventExecutor exe : exes) {
			try {
				Method m = exe.getEventClass().getMethod("getHandlerList", null);
				HandlerList h = (HandlerList) m.invoke(null, null);
				h.registerExecutor(exe, handleroptions);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException 
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static List<EventExecutor> getExecutors(Listener listener) {
		if (listener == null) return null;
		List<EventExecutor> executors = new ArrayList<>();
		for (Method method : listener.getClass().getMethods()) {
			EventHandler handler = method.getAnnotation(EventHandler.class);
			if (handler == null) continue;
			if (method.getParameterCount() != 1) continue;
			Class<?>[] params = method.getParameterTypes();
			if (!params[0].isAssignableFrom(Event.class)) continue;
			method.setAccessible(true);
			executors.add(new EventExecutor(params[0], method, handler.priority(), handler.ignoreCancelled(), listener));
		}
		return executors;
	}

}
