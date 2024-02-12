package de.atlascore.io.protocol.play;

import java.io.IOException;

import de.atlasmc.io.ConnectionHandler;
import de.atlasmc.io.Packet;
import de.atlasmc.io.PacketIO;
import de.atlasmc.io.protocol.play.PacketInSetPlayerRotation;
import io.netty.buffer.ByteBuf;

public class CorePacketInSetPlayerRotation implements PacketIO<PacketInSetPlayerRotation> {
	
	@Override
	public void read(PacketInSetPlayerRotation packet, ByteBuf in, ConnectionHandler con) throws IOException {
		packet.setYaw(in.readFloat());
		packet.setPitch(in.readFloat());
		packet.setOnGround(in.readBoolean());
	}

	@Override
	public void write(PacketInSetPlayerRotation packet, ByteBuf out, ConnectionHandler con) throws IOException {
		out.writeFloat(packet.getYaw());
		out.writeFloat(packet.getPitch());
		out.writeBoolean(packet.isOnGround());
	}

	@Override
	public PacketInSetPlayerRotation createPacketData() {
		return new PacketInSetPlayerRotation();
	}

	@Override
	public int getPacketID() {
		return Packet.getDefaultPacketID(PacketInSetPlayerRotation.class);
	}	

}
