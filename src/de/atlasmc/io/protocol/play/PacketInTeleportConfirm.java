package de.atlasmc.io.protocol.play;

import de.atlasmc.io.DefaultPacketID;
import de.atlasmc.io.PacketInbound;

@DefaultPacketID(PacketPlay.IN_TELEPORT_CONFIRM)
public interface PacketInTeleportConfirm extends PacketPlay, PacketInbound {

	public int getTeleportID();
	
	@Override
	default int getDefaultID() {
		return IN_TELEPORT_CONFIRM;
	}
	
}
