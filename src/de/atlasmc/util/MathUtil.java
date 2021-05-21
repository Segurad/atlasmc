package de.atlasmc.util;

import de.atlasmc.Location;
import de.atlasmc.SimpleLocation;
import de.atlasmc.world.World;

public class MathUtil {
	
	private MathUtil() {}
	
	/**
	 * 
	 * @param original
	 * @param min
	 * @param max
	 * @return the original if it is in range otherwise it will return the min or max
	 */
	public static double getInRange(double original, double min, double max) {
		if (original > max)
			return max;
		if (original < min) {
			return min;
		}
		return original;
	}
	
	public static boolean isInRange(double original, double min, double max) {
		return original >= min && original <= max;
	}

	public static int getPositionX(long position) {
		return (int) (position >> 38);
	}
	
	public static int getPositionY(long position) {
		return (int) (position & 0xFFF);
	}
	
	public static int getPositionZ(long position) {
		return (int) (position << 26 >> 38);
	}
	
	public static long toPosition(int x, int y, int z) {
		return ((x & 0x3FFFFFF) << 38) |
		((z & 0x3FFFFFF) << 12) |
		(y & 0xFFF);
	}

	public static long toPosition(double x, double y, double z) {
		return toPosition((int) x, (int) y,  (int) z);
	}

	public static int toAngle(float value) {
		return Math.round(value * 256 / 360);
	}
	
	public static float fromAngle(int value) {
		return value * 360 / 256;
	}

	public static short delta(double var0, double var1) {
		return (short) ((var0 * 32 - var1 * 32) * 128);
	}
	
	public long sectionToPosition(int x, int y, int z) {
		return ((x & 0x3FFFFF) << 42) | (y & 0xFFFFF) | ((z & 0x3FFFFF) << 20);
	}
	
	public int sectionPositionX(long section) {
		return (int) (section >> 42);
	}
	
	public int sectionPositionY(long section) {
		long y = section << 42 >> 44;
		return (int) y;
	}
	
	public int sectionPositionZ(long section) {
		long z = section << 22 >> 42;
		return (int) z;
	}
	
	/**
	 * 
	 * @param blockstate
	 * @param x relative to chunk
	 * @param y relative to chunk
	 * @param z relative to chunk
	 * @return
	 */
	public long blockdataPosition(int blockstate, int x, int y, int z) {
		return blockstate << 12 | (x << 8 | y << 4 | z);
	}

	public static Location getLocation(World world, long position) {
		return new Location(world, getPositionX(position), getPositionY(position), getPositionZ(position));
	}
	
	public static SimpleLocation getLocation(long position) {
		return new SimpleLocation(getPositionX(position), getPositionY(position), getPositionZ(position));
	}
	
}
