package de.atlascore.io.protocol.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.atlasmc.chat.Chat;
import de.atlasmc.io.ConnectionHandler;
import de.atlasmc.io.PacketIO;
import de.atlasmc.io.protocol.common.AbstractPacketServerLinks;
import de.atlasmc.io.protocol.common.AbstractPacketServerLinks.Label;
import de.atlasmc.io.protocol.common.AbstractPacketServerLinks.ServerLink;
import io.netty.buffer.ByteBuf;
import static de.atlasmc.io.protocol.ProtocolUtil.*;

public abstract class CoreAbstractPacketServerLinks<T extends AbstractPacketServerLinks> implements PacketIO<T> {

	@Override
	public void read(T packet, ByteBuf in, ConnectionHandler con) throws IOException {
		final int count = readVarInt(in);
		if (count == 0) {
			packet.links = List.of();
			return;
		}
		ArrayList<ServerLink> links = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			Chat customLabel = null;
			Label label = null;
			if (in.readBoolean()) {
				label = Label.getByID(readVarInt(in));
			} else {
				customLabel = readTextComponent(in);
			}
			String url = readString(in);
			links.add(new ServerLink(label, customLabel, url));
		}
	}

	@Override
	public void write(T packet, ByteBuf out, ConnectionHandler con) throws IOException {
		List<ServerLink> links = packet.links;
		final int count = links.size();
		writeVarInt(count, out);
		for (int i = 0; i < count; i++) {
			ServerLink link = links.get(i);
			if (link.label != null) {
				out.writeBoolean(true);
				writeVarInt(link.label.getID(), out);
			} else {
				writeTextComponent(link.customLabel, out);
			}
			writeString(link.url, out);
		}
	}

}
