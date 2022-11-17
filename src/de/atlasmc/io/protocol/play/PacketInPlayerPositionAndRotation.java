package de.atlasmc.io.protocol.play;

import de.atlasmc.SimpleLocation;
import de.atlasmc.io.DefaultPacketID;
import de.atlasmc.io.PacketInbound;

@DefaultPacketID(PacketPlay.IN_PLAYER_POSITION_AND_ROTATION)
public interface PacketInPlayerPositionAndRotation extends PacketPlay, PacketInbound {
	
	public double getX();
	
	public double getFeetY();
	
	public double getZ();
	
	public float getYaw();
	
	public float getPitch();
	
	public boolean isOnGround();
	
	/**
	 * Applies all Location changes to the Location
	 * @param loc
	 */
	public void getLocation(SimpleLocation loc);
	
	@Override
	default int getDefaultID() {
		return IN_PLAYER_POSITION_AND_ROTATION;
	}

}
