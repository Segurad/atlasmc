package de.atlasmc.io.protocol.play;

import de.atlasmc.io.Packet;

public interface PacketInQueryBlockNBT extends Packet {

	public int getTransactionID();
	public long getLocation();
}
