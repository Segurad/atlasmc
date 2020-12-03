package de.atlasmc.server;

import java.util.List;

public class AtlasNetwork {
	
	private List<ServerGroup> groups;
	private List<ServerPlayer> players;
	private List<AtlasNode> nodes;
	private AtlasNode master;
	private ServerGroup fallBack;
	
	public AtlasNode getMaster() {
		return master;
	}
	
	public ServerGroup getFallBack() {
		return fallBack;
	}
	
	public void setFallBack(ServerGroup group) {
		this.fallBack = group;
	}

}
