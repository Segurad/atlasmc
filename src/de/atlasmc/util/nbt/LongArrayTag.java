package de.atlasmc.util.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class LongArrayTag extends AbstractTag {

	private long[] data;
	
	public LongArrayTag(String name, long[] data) {
		this.data = data;
		this.name = name;
	}
	
	public LongArrayTag() {}

	@Override
	public long[] getData() {
		return data;
	}

	@Override
	public TagType getType() {
		return TagType.LONG_ARRAY;
	}

	@Override
	void readD(DataInput input, boolean readName) throws IOException {
		final int len = input.readInt();
		data = new long[len];
		for (int i = 0; i < len; i++) {
			data[i] = input.readLong();
		}
	}

	@Override
	void writeD(DataOutput output, boolean readName) throws IOException {
		output.writeInt(data.length);
		for (long i : data) {
			output.writeLong(i);
		}
	}

}
