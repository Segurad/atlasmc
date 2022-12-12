package de.atlascore.io.protocol.play;

import java.io.IOException;

import de.atlascore.io.CoreAbstractHandler;
import de.atlasmc.io.ConnectionHandler;
import de.atlasmc.io.protocol.play.PacketInPlayerMovement;
import io.netty.buffer.ByteBuf;

public class CorePacketInPlayerMovement extends CoreAbstractHandler<PacketInPlayerMovement> {

	@Override
	public void read(PacketInPlayerMovement packet, ByteBuf in, ConnectionHandler con) throws IOException {
		packet.setOnGround(in.readBoolean());
	}

	@Override
	public void write(PacketInPlayerMovement packet, ByteBuf out, ConnectionHandler con) throws IOException {
		out.writeBoolean(packet.isOnGround());
	}
	
	@Override
	public PacketInPlayerMovement createPacketData() {
		return new PacketInPlayerMovement();
	}

}
