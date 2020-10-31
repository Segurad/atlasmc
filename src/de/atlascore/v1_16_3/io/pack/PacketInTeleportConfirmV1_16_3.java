package de.atlascore.v1_16_3.io.pack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.atlascore.v1_16_3.io.V1_16_3;
import de.atlasmc.io.AbstractPacket;
import de.atlasmc.io.pack.PacketInTeleportConfirm;

public class PacketInTeleportConfirmV1_16_3 extends AbstractPacket implements PacketInTeleportConfirm {

	public PacketInTeleportConfirmV1_16_3() {
		super(0x00, V1_16_3.version);
	}

	private int teleportID;

	@Override
	public void read(int length, DataInputStream input) throws IOException {
		teleportID = readVarInt(input);
	}

	@Override
	public void write(DataOutputStream output) {
		
	}

	@Override
	public int getTeleportID() {
		return teleportID;
	}

}
