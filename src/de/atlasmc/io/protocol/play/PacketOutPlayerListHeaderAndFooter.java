package de.atlasmc.io.protocol.play;

import de.atlasmc.chat.component.ChatComponent;
import de.atlasmc.io.DefaultPacketID;
import de.atlasmc.io.PacketOutbound;

@DefaultPacketID(PacketPlay.OUT_PLAYER_LIST_HEADER_AND_FOOTER)
public interface PacketOutPlayerListHeaderAndFooter extends PacketPlay, PacketOutbound {
	
	public ChatComponent getHeader();
	public ChatComponent getFooter();
	
	@Override
	public default int getDefaultID() {
		return OUT_PLAYER_LIST_HEADER_AND_FOOTER;
	}

}
