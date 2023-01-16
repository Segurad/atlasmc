package de.atlascore.io.protocol.play;

import java.io.IOException;

import static de.atlasmc.io.AbstractPacket.*;

import de.atlascore.io.ConnectionHandler;
import de.atlasmc.io.PacketIO;
import de.atlasmc.io.protocol.play.PacketOutRemoveEntityEffect;
import io.netty.buffer.ByteBuf;

public class CorePacketOutRemoveEntityEffect extends PacketIO<PacketOutRemoveEntityEffect> {

	@Override
	public void read(PacketOutRemoveEntityEffect packet, ByteBuf in, ConnectionHandler handler) throws IOException {
		packet.setEntityID(readVarInt(in));
		packet.setEffectID(in.readByte());
	}

	@Override
	public void write(PacketOutRemoveEntityEffect packet, ByteBuf out, ConnectionHandler handler) throws IOException {
		writeVarInt(packet.getEntityID(), out);
		out.writeByte(packet.getEffectID());
	}

	@Override
	public PacketOutRemoveEntityEffect createPacketData() {
		return new PacketOutRemoveEntityEffect();
	}

}
