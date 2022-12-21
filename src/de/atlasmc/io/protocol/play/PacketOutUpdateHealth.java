package de.atlasmc.io.protocol.play;

import de.atlasmc.io.AbstractPacket;
import de.atlasmc.io.DefaultPacketID;

@DefaultPacketID(PacketPlay.OUT_UPDATE_HEALTH)
public class PacketOutUpdateHealth extends AbstractPacket implements PacketPlayOut {
	
	private float health, saturation;
	private int food;
	
	public float getHealth() {
		return health;
	}
	
	public void setHealth(float health) {
		this.health = health;
	}
	
	public float getSaturation() {
		return saturation;
	}
	
	public void setSaturation(float saturation) {
		this.saturation = saturation;
	}
	
	public int getFood() {
		return food;
	}
	
	public void setFood(int food) {
		this.food = food;
	}
	
	@Override
	public int getDefaultID() {
		return OUT_UPDATE_HEALTH;
	}

}
