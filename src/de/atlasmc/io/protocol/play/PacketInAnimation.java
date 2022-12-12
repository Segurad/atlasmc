package de.atlasmc.io.protocol.play;

import de.atlasmc.io.AbstractPacket;
import de.atlasmc.io.DefaultPacketID;

@DefaultPacketID(PacketPlay.IN_ANIMATION)
public class PacketInAnimation extends AbstractPacket implements PacketPlayIn {
	
	private int hand;
	
	public int getHand() {
		return hand;
	}
	
	public void setHand(int hand) {
		this.hand = hand;
	}
	
	@Override
	public int getDefaultID() {
		return IN_ANIMATION;
	}

}
