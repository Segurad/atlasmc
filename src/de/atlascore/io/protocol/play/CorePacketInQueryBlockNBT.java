package de.atlascore.io.protocol.play;

import java.io.IOException;

import de.atlascore.io.CoreAbstractHandler;
import static de.atlasmc.io.AbstractPacket.*;
import de.atlasmc.io.ConnectionHandler;
import de.atlasmc.io.protocol.play.PacketInQueryBlockNBT;
import io.netty.buffer.ByteBuf;

public class CorePacketInQueryBlockNBT extends CoreAbstractHandler<PacketInQueryBlockNBT> {
	
	@Override
	public void read(PacketInQueryBlockNBT packet, ByteBuf in, ConnectionHandler handler) throws IOException {
		packet.setTransactionID(readVarInt(in));
		packet.setPosition(in.readLong());
	}

	@Override
	public void write(PacketInQueryBlockNBT packet, ByteBuf out, ConnectionHandler handler) throws IOException {
		writeVarInt(packet.getTransactionID(), out);
		out.writeLong(packet.getPosition());
	}

	@Override
	public PacketInQueryBlockNBT createPacketData() {
		return new PacketInQueryBlockNBT();
	}

}
