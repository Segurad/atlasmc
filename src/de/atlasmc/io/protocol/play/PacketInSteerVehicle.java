package de.atlasmc.io.protocol.play;

import de.atlasmc.io.Packet;

public interface PacketInSteerVehicle extends Packet {
	
	public float getSideways();
	public float getForward();
	public byte getFlags();
	
	@Override
	default int getDefaultID() {
		return 0x1D;
	}

}
