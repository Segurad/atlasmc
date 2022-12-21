package de.atlascore.io.protocol.play;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.atlascore.io.CoreAbstractHandler;
import static de.atlasmc.io.AbstractPacket.*;
import de.atlasmc.io.ConnectionHandler;
import de.atlasmc.io.protocol.play.PacketOutTabComplete;
import de.atlasmc.io.protocol.play.PacketOutTabComplete.Match;
import io.netty.buffer.ByteBuf;

public class CorePacketOutTabComplete extends CoreAbstractHandler<PacketOutTabComplete> {

	@Override
	public void read(PacketOutTabComplete packet, ByteBuf in, ConnectionHandler handler) throws IOException {
		packet.setTransactionID(readVarInt(in));
		packet.setStart(readVarInt(in));
		packet.setLength(readVarInt(in));
		int count = readVarInt(in);
		List<Match> matches = new ArrayList<>(count);
		while (count > 0) {
			String match = readString(in);
			boolean hasToolTip = in.readBoolean();
			String toolTip = null;
			if (hasToolTip) toolTip = readString(in);
			matches.add(new Match(match, toolTip));
		}
		packet.setMatches(matches);
	}

	@Override
	public void write(PacketOutTabComplete packet, ByteBuf out, ConnectionHandler handler) throws IOException {
		writeVarInt(packet.getTransactionID(), out);
		writeVarInt(packet.getStart(), out);
		writeVarInt(packet.getLength(), out);
		writeVarInt(packet.getMatches().size(), out);
		for (Match m : packet.getMatches()) {
			writeString(m.getMatch(), out);
			out.writeBoolean(m.hasToolTip());
			if (m.hasToolTip()) 
				writeString(m.getToolTip(), out);
		}
	}
	
	@Override
	public PacketOutTabComplete createPacketData() {
		return new PacketOutTabComplete();
	}

}
