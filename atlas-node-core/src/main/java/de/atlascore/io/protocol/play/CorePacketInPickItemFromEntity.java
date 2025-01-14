package de.atlascore.io.protocol.play;

import java.io.IOException;

import de.atlasmc.io.ConnectionHandler;
import de.atlasmc.io.Packet;
import de.atlasmc.io.PacketIO;
import de.atlasmc.io.protocol.play.PacketInPickItemFromEntity;
import io.netty.buffer.ByteBuf;
import static de.atlasmc.io.protocol.ProtocolUtil.*;

public class CorePacketInPickItemFromEntity implements PacketIO<PacketInPickItemFromEntity> {

	@Override
	public void read(PacketInPickItemFromEntity packet, ByteBuf in, ConnectionHandler con) throws IOException {
		packet.slotToUse = readVarInt(in);
	}

	@Override
	public void write(PacketInPickItemFromEntity packet, ByteBuf out, ConnectionHandler con) throws IOException {
		writeVarInt(packet.slotToUse, out);
	}

	@Override
	public PacketInPickItemFromEntity createPacketData() {
		return new PacketInPickItemFromEntity();
	}

	@Override
	public int getPacketID() {
		return Packet.getDefaultPacketID(PacketInPickItemFromEntity.class);
	}

}
