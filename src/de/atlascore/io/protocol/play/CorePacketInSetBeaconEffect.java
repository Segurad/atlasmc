package de.atlascore.io.protocol.play;

import java.io.IOException;

import de.atlascore.io.CoreAbstractHandler;
import static de.atlasmc.io.AbstractPacket.*;

import de.atlasmc.io.ConnectionHandler;
import de.atlasmc.io.protocol.play.PacketInSetBeaconEffect;
import io.netty.buffer.ByteBuf;

public class CorePacketInSetBeaconEffect extends CoreAbstractHandler<PacketInSetBeaconEffect> {
	
	@Override
	public void read(PacketInSetBeaconEffect packet, ByteBuf in, ConnectionHandler handler) throws IOException {
		packet.setPrimaryEffect(readVarInt(in));
		packet.setPrimaryEffect(readVarInt(in));	
	}

	@Override
	public void write(PacketInSetBeaconEffect packet, ByteBuf out, ConnectionHandler handler) throws IOException {
		writeVarInt(packet.getPrimaryEffect(), out);
		writeVarInt(packet.getSecondaryEffect(), out);
	}

	@Override
	public PacketInSetBeaconEffect createPacketData() {
		return new PacketInSetBeaconEffect();
	}

}
