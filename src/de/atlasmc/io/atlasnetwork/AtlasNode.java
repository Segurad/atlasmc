package de.atlasmc.io.atlasnetwork;

import java.util.List;

import de.atlasmc.io.atlasnetwork.server.Server;

/**
 * Represents a AtlasNode
 * @author Segurad
 *
 */
public interface AtlasNode {

	public AtlasNode getMaster();
	public boolean isMaster();
	public List<? extends Server> getServers();
	public List<Proxy> getProxys();
	
}
