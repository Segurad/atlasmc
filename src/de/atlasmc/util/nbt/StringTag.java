package de.atlasmc.util.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class StringTag extends AbstractTag {

	private String data;
	
	public StringTag(String name, String data) {
		this.data = data;
		this.name = name;
	}
	
	public StringTag() {}

	@Override
	public String getData() {
		return data;
	}

	@Override
	public TagType getType() {
		return TagType.STRING;
	}

	@Override
	void readD(DataInputStream input, boolean readName) throws IOException {
		int len = input.readShort();
		byte[] buffer = new byte[len];
		data = new String(buffer);
	}

	@Override
	void writeD(DataOutputStream output, boolean readName) throws IOException {
		byte[] buffer = data.getBytes();
		output.writeShort(buffer.length);
		output.write(buffer);
	}

}
