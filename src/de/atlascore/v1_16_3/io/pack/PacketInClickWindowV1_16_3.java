package de.atlascore.v1_16_3.io.pack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.atlascore.v1_16_3.io.V1_16_3;
import de.atlasmc.inventory.ItemStack;
import de.atlasmc.io.AbstractPacket;
import de.atlasmc.io.pack.PacketInClickWindow;

public class PacketInClickWindowV1_16_3 extends AbstractPacket implements PacketInClickWindow {

	public PacketInClickWindowV1_16_3() {
		super(0x09, V1_16_3.version);
	}
	
	private byte windowID, button;
	private short slot, action;
	private int mode;
	private ItemStack clickedItem;

	@Override
	public byte getWindowID() {
		return windowID;
	}

	@Override
	public short getSlot() {
		return slot;
	}

	@Override
	public byte getButton() {
		return button;
	}

	@Override
	public short getActionNumber() {
		return action;
	}

	@Override
	public int getMode() {
		return mode;
	}

	@Override
	public void read(int length, DataInputStream input) throws IOException {
		windowID = input.readByte();
		slot = input.readShort();
		button = input.readByte();
		action = input.readShort();
		mode = readVarInt(input);
		clickedItem = readSlot(input);
	}

	@Override
	public void write(DataOutputStream output) throws IOException {}

	@Override
	public ItemStack getClickedItem() {
		return clickedItem;
	}

}
