package de.atlasmc.io.channel;

import de.atlasmc.io.AbstractPacket;
import de.atlasmc.io.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
	
	@Override
	protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
		AbstractPacket.writeVarInt(msg.getID(), out);
		msg.write(out);
	}

}
