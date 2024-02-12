package de.atlascore.io.protocol.play;

import java.io.IOException;

import static de.atlasmc.io.AbstractPacket.*;

import de.atlasmc.io.ConnectionHandler;
import de.atlasmc.io.Packet;
import de.atlasmc.io.PacketIO;
import de.atlasmc.io.protocol.play.PacketOutUpdateSectionBlocks;
import io.netty.buffer.ByteBuf;

public class CorePacketOutUpdateSectionBlocks implements PacketIO<PacketOutUpdateSectionBlocks> {
	
	@Override
	public void read(PacketOutUpdateSectionBlocks packet, ByteBuf in, ConnectionHandler handler) throws IOException {
		packet.setSection(in.readLong());
		final int size = readVarInt(in);
		long[] blocks = new long[size];
		for (int i = 0; i < size; i++) {
			blocks[i] = readVarLong(in);
		}
		packet.setBlocks(blocks);
	}

	@Override
	public void write(PacketOutUpdateSectionBlocks packet, ByteBuf out, ConnectionHandler handler) throws IOException {
		out.writeLong(packet.getSection());
		writeVarInt(packet.getBlocks().length, out);
		for (long l : packet.getBlocks()) {
			writeVarLong(l, out);
		}
	}

	@Override
	public PacketOutUpdateSectionBlocks createPacketData() {
		return new PacketOutUpdateSectionBlocks();
	}

	@Override
	public int getPacketID() {
		return Packet.getDefaultPacketID(PacketOutUpdateSectionBlocks.class);
	}

}
