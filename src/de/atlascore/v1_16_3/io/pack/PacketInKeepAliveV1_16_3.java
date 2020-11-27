package de.atlascore.v1_16_3.io.pack;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import de.atlascore.v1_16_3.io.V1_16_3;
import de.atlasmc.io.AbstractPacket;
import de.atlasmc.io.pack.PacketInKeepAlive;

public class PacketInKeepAliveV1_16_3 extends AbstractPacket implements PacketInKeepAlive {

	public PacketInKeepAliveV1_16_3() {
		super(0x10, V1_16_3.version);
	}
	
	private long keepAliveID;

	@Override
	public void read(int length, DataInput input) throws IOException {
		keepAliveID = input.readLong();
	}

	@Override
	public void write(DataOutput output) throws IOException {}

	@Override
	public long KeepAliveID() {
		return keepAliveID;
	}

}
