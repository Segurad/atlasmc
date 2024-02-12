package de.atlascore.io.protocol.configuration;

import java.io.IOException;

import de.atlasmc.io.ConnectionHandler;
import de.atlasmc.io.Packet;
import de.atlasmc.io.PacketIO;
import de.atlasmc.io.protocol.configuration.PacketInPong;
import io.netty.buffer.ByteBuf;

public class CorePacketInPong implements PacketIO<PacketInPong> {

	@Override
	public void read(PacketInPong packet, ByteBuf in, ConnectionHandler con) throws IOException {
		packet.pong = in.readInt();
	}

	@Override
	public void write(PacketInPong packet, ByteBuf out, ConnectionHandler con) throws IOException {
		out.writeInt(packet.pong);
	}

	@Override
	public PacketInPong createPacketData() {
		return new PacketInPong();
	}

	@Override
	public int getPacketID() {
		return Packet.getDefaultPacketID(PacketInPong.class);
	}

}
