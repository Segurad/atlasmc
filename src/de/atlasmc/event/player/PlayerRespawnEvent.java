package de.atlasmc.event.player;

import de.atlasmc.atlasnetwork.server.LocalServer;
import de.atlasmc.entity.Player;
import de.atlasmc.event.GenericEvent;
import de.atlasmc.event.ServerHandlerList;

public class PlayerRespawnEvent extends GenericEvent<LocalServer, ServerHandlerList>{

	private static final ServerHandlerList handlers = new ServerHandlerList();
	private final Player player;
	
	public PlayerRespawnEvent(Player player) {
		super(player.getServer());
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}

	@Override
	public ServerHandlerList getHandlers() {
		return handlers;
	}
	
	public static ServerHandlerList getHandlerList() {
		return handlers;
	}

}