package de.atlascore.log;

import org.apache.logging.log4j.core.Logger;

import de.atlasmc.atlasnetwork.server.LocalServer;
import de.atlasmc.log.ServerLog;

public class CoreServerLog extends CoreLog implements ServerLog {

	private final LocalServer server;
	
	public CoreServerLog(LocalServer server, Logger log) {
		super(server.getServerName(), log);
		this.server = server;
	}

	@Override
	public LocalServer getServer() {
		return server;
	}

}
