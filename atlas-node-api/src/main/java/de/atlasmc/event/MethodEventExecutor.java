package de.atlasmc.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import de.atlasmc.plugin.Plugin;

/**
 * EventExecutor implementation that invokes methods
 */
public class MethodEventExecutor extends AbstractEventExecutor {
	
	private final Method method;
	
	public MethodEventExecutor(Plugin plugin, Class<? extends Event> eventClass, Method method, EventPriority priority, boolean ignoreCancelled, Listener listener) {
		super(plugin, eventClass, ignoreCancelled, priority, listener);
		this.method = method;
		method.setAccessible(true);
	}	
	
	/**
	 * Returns a list of {@link EventExecutor}s created by the methods marked with {@link EventHandler} of the Listener class
	 * @param listener
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public static List<EventExecutor> getExecutors(Plugin plugin, Listener listener) {
		if (listener == null)
			throw new IllegalArgumentException("Listener can not be null!");
		List<EventExecutor> executors = null;
		for (Method method : listener.getClass().getMethods()) {
			EventHandler handler = method.getAnnotation(EventHandler.class);
			if (handler == null) 
				continue;
			if (method.getParameterCount() != 1) 
				continue;
			Class<?>[] params = method.getParameterTypes();
			if (!Event.class.isAssignableFrom(params[0])) 
				continue;
			if (executors == null)
				executors = new ArrayList<>();
			executors.add(new MethodEventExecutor(plugin, (Class<? extends Event>) params[0], method, handler.priority(), handler.ignoreCancelled(), listener));
		}
		return executors == null ? List.of() : executors;
	}

	@Override
	protected void internalFireEvent(Event event) throws Exception{
		method.invoke(getListener(), event);
	}
	
}
