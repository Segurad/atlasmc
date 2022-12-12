package de.atlasmc.io.protocol.play;

import de.atlasmc.io.AbstractPacket;
import de.atlasmc.io.DefaultPacketID;

@DefaultPacketID(PacketPlay.IN_HELD_ITEM_CHANGE)
public class PacketInHeldItemChange extends AbstractPacket implements PacketPlayIn {
	
	private int slot;

	public int getSlot() {
		return slot;
	}
	
	public void setSlot(int slot) {
		this.slot = slot;
	}
	
	@Override
	public int getDefaultID() {
		return IN_HELD_ITEM_CHANGE;
	}
	
}
