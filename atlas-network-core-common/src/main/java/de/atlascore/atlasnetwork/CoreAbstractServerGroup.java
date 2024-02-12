package de.atlascore.atlasnetwork;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import de.atlasmc.atlasnetwork.server.LocalServer;
import de.atlasmc.atlasnetwork.server.ServerConfig;
import de.atlasmc.atlasnetwork.server.ServerGroup;
import de.atlasmc.util.configuration.ConfigurationSection;

public abstract class CoreAbstractServerGroup implements ServerGroup {
	
	protected final String name;
	protected final ServerConfig config;
	protected final int maxServers;
	protected final int maxNonFullServers;
	protected final int minServers;
	protected final int minNonFullServers;
	protected final int newServerDelay;
	protected final float newServerOnUserLoad;
	protected volatile boolean maintenance;
	protected final Map<UUID, LocalServer> localServers;
	
	public CoreAbstractServerGroup(String name, ConfigurationSection config) {
		if (name == null)
			throw new IllegalArgumentException("Name can not be null!");
		if (config == null)
			throw new IllegalArgumentException("Config can not be null!");
		this.name = name;
		minServers = config.getInt("min-server");
		maxServers = config.getInt("max-server");
		newServerDelay = config.getInt("new-server-delay");
		newServerOnUserLoad = config.getFloat("new-server-on-user-load");
		maxNonFullServers = config.getInt("max-non-full-server");
		minNonFullServers = config.getInt("min-non-full-server");
		maintenance = config.getBoolean("maintenance");
		ConfigurationSection serverCfg = config.getConfigurationSection("server-config");
		this.config = new ServerConfig(serverCfg);
		this.localServers = new ConcurrentHashMap<>();
	}
	
	// config
	@Override
	public ServerConfig getServerConfig() {
		return config;
	}
	
	@Override
	public int getMaxNonFullServers() {
		return maxNonFullServers;
	}
	
	@Override
	public int getMinNonFullServers() {
		return minNonFullServers;
	}
	
	@Override
	public int getMaxServers() {
		return maxServers;
	}
	
	@Override
	public int getMinServers() {
		return minServers;
	}
	
	@Override
	public int getNewServerDelay() {
		return newServerDelay;
	}
	
	@Override
	public float getNewServerOnUserLoad() {
		return newServerOnUserLoad;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public boolean isMaintenance() {
		return maintenance;
	}
	
	@Override
	public LocalServer getLocalServer(UUID uuid) {
		if (uuid == null)
			throw new IllegalArgumentException("UUID can not be null!");
		return localServers.get(uuid);
	}
	
	@Override
	public Collection<LocalServer> getLocalServers() {
		return localServers.values();
	}

}
