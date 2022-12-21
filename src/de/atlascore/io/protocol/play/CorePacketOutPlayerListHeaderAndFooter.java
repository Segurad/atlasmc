package de.atlascore.io.protocol.play;

import java.io.IOException;

import de.atlascore.io.CoreAbstractHandler;
import static de.atlasmc.io.AbstractPacket.*;
import de.atlasmc.io.ConnectionHandler;
import de.atlasmc.io.protocol.play.PacketOutPlayerListHeaderAndFooter;
import io.netty.buffer.ByteBuf;

public class CorePacketOutPlayerListHeaderAndFooter extends CoreAbstractHandler<PacketOutPlayerListHeaderAndFooter> {

	@Override
	public void read(PacketOutPlayerListHeaderAndFooter packet, ByteBuf in, ConnectionHandler handler) throws IOException {
		packet.setHeader(readString(in));
		packet.setFooter(readString(in));
	}

	@Override
	public void write(PacketOutPlayerListHeaderAndFooter packet, ByteBuf out, ConnectionHandler handler) throws IOException {
		writeString(packet.getHeader(), out);
		writeString(packet.getFooter(), out);
	}

	@Override
	public PacketOutPlayerListHeaderAndFooter createPacketData() {
		return new PacketOutPlayerListHeaderAndFooter();
	}

}
