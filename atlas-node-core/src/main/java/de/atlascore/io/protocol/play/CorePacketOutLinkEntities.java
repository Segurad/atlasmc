package de.atlascore.io.protocol.play;

import java.io.IOException;

import de.atlasmc.io.ConnectionHandler;
import de.atlasmc.io.Packet;
import de.atlasmc.io.PacketIO;
import de.atlasmc.io.protocol.play.PacketOutLinkEntities;
import io.netty.buffer.ByteBuf;

public class CorePacketOutLinkEntities implements PacketIO<PacketOutLinkEntities> {

	@Override
	public void read(PacketOutLinkEntities packet, ByteBuf in, ConnectionHandler handler) throws IOException {
		packet.setAttachedEntityID(in.readInt());
		packet.setHolderEntityID(in.readInt());
	}

	@Override
	public void write(PacketOutLinkEntities packet, ByteBuf out, ConnectionHandler handler) throws IOException {
		out.writeInt(packet.getAttachedEntityID());
		out.writeInt(packet.getHolderEntityID());
	}

	@Override
	public PacketOutLinkEntities createPacketData() {
		return new PacketOutLinkEntities();
	}

	@Override
	public int getPacketID() {
		return Packet.getDefaultPacketID(PacketOutLinkEntities.class);
	}

}
