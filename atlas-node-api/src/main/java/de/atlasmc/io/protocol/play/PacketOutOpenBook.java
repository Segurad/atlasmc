package de.atlasmc.io.protocol.play;

import de.atlasmc.inventory.EquipmentSlot;
import de.atlasmc.io.AbstractPacket;
import de.atlasmc.io.DefaultPacketID;

@DefaultPacketID(packetID = PacketPlay.OUT_OPEN_BOOK, definition = "open_book")
public class PacketOutOpenBook extends AbstractPacket implements PacketPlayOut {
	
	public EquipmentSlot hand;
	
	@Override
	public int getDefaultID() {
		return OUT_OPEN_BOOK;
	}

}
