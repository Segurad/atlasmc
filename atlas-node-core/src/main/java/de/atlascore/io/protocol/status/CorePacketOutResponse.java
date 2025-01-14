package de.atlascore.io.protocol.status;

import java.io.IOException;
import java.io.StringReader;

import static de.atlasmc.io.protocol.ProtocolUtil.*;

import de.atlasmc.io.ConnectionHandler;
import de.atlasmc.io.Packet;
import de.atlasmc.io.PacketIO;
import de.atlasmc.io.protocol.status.PacketOutResponse;
import de.atlasmc.util.configuration.file.JsonConfiguration;
import io.netty.buffer.ByteBuf;

public class CorePacketOutResponse implements PacketIO<PacketOutResponse> {

	@Override
	public void read(PacketOutResponse packet, ByteBuf in, ConnectionHandler handler) throws IOException {
		String rawResponse = readString(in);
		packet.response = JsonConfiguration.loadConfiguration(new StringReader(rawResponse));
	}

	@Override
	public void write(PacketOutResponse packet, ByteBuf out, ConnectionHandler handler) throws IOException {
		String rawResponse = packet.response.saveToString();
		writeString(rawResponse, out);
	}
	
	@Override
	public PacketOutResponse createPacketData() {
		return new PacketOutResponse();
	}

	@Override
	public int getPacketID() {
		return Packet.getDefaultPacketID(PacketOutResponse.class);
	}

}
