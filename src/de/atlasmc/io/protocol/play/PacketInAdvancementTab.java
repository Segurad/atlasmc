package de.atlasmc.io.protocol.play;

import de.atlasmc.io.Packet;

public interface PacketInAdvancementTab extends Packet {
	
	public int getAction();
	public String getTabID();

}
