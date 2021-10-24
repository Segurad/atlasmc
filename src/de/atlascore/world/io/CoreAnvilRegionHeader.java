package de.atlascore.world.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Class that stores the header information of a .mcr file
 */
public class CoreAnvilRegionHeader {
	
	private final byte[] sectorCounts;
	private final int[] offsets;
	private final int[] timestamps;
	
	public CoreAnvilRegionHeader() {
		sectorCounts = new byte[1024];
		offsets = new int[1024];
		timestamps = new int[1024];
	}
	
	/**
	 * Reads the header data from the InputStream<br>
	 * Reads a total of 8192 Bytes
	 * @param input
	 * @throws IOException
	 */
	public void readHeader(InputStream input) throws IOException {
		for (int i = 0; i < 4096; i++) { // read location
			offsets[i] = input.read() << 16 | input.read() << 8 | input.read();
			sectorCounts[i] = (byte) input.read();
		}
		for (int i = 0; i < 4096; i++) { // read timestamps
			timestamps[i] = input.read() << 8 + input.read() << 8 + input.read() << 8 + input.read();
		}
	}
	
	/**
	 * Writes the current header data to the OutputStream<br>
	 * The header does need a total space of 8192 Bytes
	 * @param output
	 * @throws IOException
	 */
	public void writeHeader(OutputStream output) throws IOException {
		for (int i = 0; i < 4096; i++) {
			int offset = offsets[i];
			output.write(offset >> 16);
			output.write(offset >> 8);
			output.write(offset);
			output.write(sectorCounts[i]);
		}
		for (int timestamp : timestamps) {
			output.write(timestamp >> 24);
			output.write(timestamp >> 16);
			output.write(timestamp >> 8);
			output.write(timestamp);
		}
	}
	
	/**
	 * Returns the offset in 4kb sectors in the .mcr file<br>
	 * if the chunk is not present in the file (e.g. it hasn't been generated yet) this method will return 0
	 * @param x the chunk x
	 * @param z the chunk z
	 * @return the offset
	 */
	public int getOffset(int x, int z) {
		return offsets[getIndex(x, z)];
	}
	
	/**
	 * Returns the number of sectors in the .mcr file<br>
	 * if the chunk is not present in the file (e.g. it hasn't been generated yet) this method will return 0
	 * @param x the chunk x
	 * @param z the chunk z
	 * @return the sector count
	 */
	public int getSectorCount(int x, int z) {
		return sectorCounts[getIndex(x, z)];
	}
	
	/**
	 * Returns the time in seconds the chunk was last updated
	 * @param x the chunk x
	 * @param z the chunk z
	 * @return the timestamp
	 */
	public int getTimestamp(int x, int z) {
		return timestamps[getIndex(x, z)];
	}
	
	/**
	 * Returns the header entry index by the chunk coordinates
	 * @param x the chunk x
	 * @param z the chunk z
	 * @return the header index
	 */
	protected int getIndex(int x, int z) {
		return (z & 0xFF) << 4 | (x & 0xFF); 
	}

}
