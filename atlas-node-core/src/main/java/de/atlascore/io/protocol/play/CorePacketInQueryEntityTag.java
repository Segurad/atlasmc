package de.atlascore.io.protocol.play;

import java.io.IOException;

import static de.atlasmc.io.protocol.ProtocolUtil.*;

import de.atlasmc.io.ConnectionHandler;
import de.atlasmc.io.Packet;
import de.atlasmc.io.PacketIO;
import de.atlasmc.io.protocol.play.PacketInQueryEntityTag;
import io.netty.buffer.ByteBuf;

public class CorePacketInQueryEntityTag implements PacketIO<PacketInQueryEntityTag> {

	@Override
	public void read(PacketInQueryEntityTag packet, ByteBuf in, ConnectionHandler handler) throws IOException {
		packet.transactionID = readVarInt(in);
		packet.entityID = readVarInt(in);
	}

	@Override
	public void write(PacketInQueryEntityTag packet, ByteBuf out, ConnectionHandler handler) throws IOException {
		writeVarInt(packet.transactionID, out);
		writeVarInt(packet.entityID, out);
	}
	
	@Override
	public PacketInQueryEntityTag createPacketData() {
		return new PacketInQueryEntityTag();
	}

	@Override
	public int getPacketID() {
		return Packet.getDefaultPacketID(PacketInQueryEntityTag.class);
	}

}
