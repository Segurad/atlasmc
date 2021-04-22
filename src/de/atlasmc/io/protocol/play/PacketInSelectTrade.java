package de.atlasmc.io.protocol.play;

import de.atlasmc.io.Packet;

public interface PacketInSelectTrade extends Packet {
	
	public int getSelectedSlot();
	
	@Override
	default int getDefaultID() {
		return 0x23;
	}

}
