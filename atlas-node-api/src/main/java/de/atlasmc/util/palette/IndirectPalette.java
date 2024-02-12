package de.atlasmc.util.palette;

import de.atlasmc.io.AbstractPacket;
import io.netty.buffer.ByteBuf;

public class IndirectPalette<E> extends AbstractIndirectPalette<E> {
	

	public IndirectPalette(int minBitsPerEntry, int capacity, int maxBitsPerEntry, GlobalValueProvider<E> provider) {
		super(minBitsPerEntry, capacity, maxBitsPerEntry, provider);
	}
	
	public IndirectPalette(int minBitsPerEntry, int maxBitsPerEntry, Palette<E> palette) {
		super(minBitsPerEntry, maxBitsPerEntry, palette);
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeByte(values.getBitsPerValue());
		// write palette
		if (highestEntry < 0) {
			AbstractPacket.writeVarInt(0, buf);
		} else {
			AbstractPacket.writeVarInt(highestEntry+1, buf);
			for (int i = 0; i <= highestEntry; i++) {
				Entry<E> entry = valueToEntry.get(i);
				AbstractPacket.writeVarInt(entry != null ? i : 0,  buf);
			}
		}
		// write data
		final long[] values = this.values.array();
		AbstractPacket.writeVarInt(values.length, buf);
		for (long value : values) {
			buf.writeLong(value);
		}
	}
	
	@Override
	public long getSerializedSize() {
		int size = 1;
		size += AbstractPacket.getVarIntLength(size());
		for (Entry<E> entry : entryMap.values()) {
			size += AbstractPacket.getVarIntLength(entry.globalValue);
		}
		int missing = size() - getEntryCount();
		if (missing > 0) {
			size += 1 * missing;
		}
		long[] values = this.values.array();
		size += AbstractPacket.getVarIntLength(values.length);
		size += values.length * 9; // use max number of bytes to represent long values as varlong
		return size;
	}
	
}
