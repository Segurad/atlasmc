package de.atlasmc.io.protocol.play;

import de.atlasmc.io.DefaultPacketID;
import de.atlasmc.io.protocol.common.AbstractPacketPing;

@DefaultPacketID(packetID = PacketPlay.OUT_PING, definition = "ping")
public class PacketOutPing extends AbstractPacketPing implements PacketPlayOut {
	
	@Override
	public int getDefaultID() {
		return OUT_PING;
	}
	
}
