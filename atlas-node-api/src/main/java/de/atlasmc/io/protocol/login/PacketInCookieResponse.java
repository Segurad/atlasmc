package de.atlasmc.io.protocol.login;

import de.atlasmc.io.DefaultPacketID;
import de.atlasmc.io.protocol.common.AbstractPacketCookieData;

@DefaultPacketID(packetID = PacketLogin.IN_COOKIE_RESPONSE, definition = "cookie_response")
public class PacketInCookieResponse extends AbstractPacketCookieData implements PacketLoginIn {
	
	@Override
	public int getDefaultID() {
		return PacketLogin.IN_COOKIE_RESPONSE;
	}
	
}
