package de.atlasmc.io.protocol.play;

import java.util.List;

import de.atlasmc.entity.Merchant.MerchantRecipe;
import de.atlasmc.io.AbstractPacket;
import de.atlasmc.io.DefaultPacketID;

@DefaultPacketID(packetID = PacketPlay.OUT_MERCHANT_OFFERS, definition = "merchant_offers")
public class PacketOutMerchantOffers extends AbstractPacket implements PacketPlayOut {
	
	public int windowID;
	public List<MerchantRecipe> trades;
	public int level;
	public int experience;
	public boolean regular;
	public boolean canRestock;

	@Override
	public int getDefaultID() {
		return OUT_MERCHANT_OFFERS;
	}

}
