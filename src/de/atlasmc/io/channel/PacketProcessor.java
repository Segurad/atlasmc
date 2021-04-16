package de.atlasmc.io.channel;

import de.atlasmc.io.ConnectionHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class PacketProcessor extends ChannelInboundHandlerAdapter {
	
	private final ConnectionHandler handler;
	
	public PacketProcessor(ConnectionHandler handler) {
		this.handler = handler;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		if (handler.hasQueued()) {
			handler.writeQueued();
		}
		ctx.fireChannelActive();
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
	}

}
