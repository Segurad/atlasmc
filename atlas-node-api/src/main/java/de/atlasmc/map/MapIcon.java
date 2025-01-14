package de.atlasmc.map;

import java.util.List;

import de.atlasmc.chat.Chat;
import de.atlasmc.util.EnumID;
import de.atlasmc.util.EnumValueCache;

public class MapIcon implements Cloneable {
	
	private IconType type;
	private int x;
	private int z;
	private float direction;
	private Chat name;
	
	public MapIcon(IconType type) {
		this(type, 0, 0);
	}
	
	public MapIcon(IconType type, int x, int z) {
		this(type, x, z, 0);
	}
	
	public MapIcon(IconType type, int x, int z, float direction) {
		this.type = type;
		this.x = x;
		this.z = z;
		this.direction = direction;
	}
	
	public enum IconType implements EnumID, EnumValueCache {
		
		WHITE_ARROW,
		GREEN_ARROW,
		RED_ARROW,
		BLUE_ARROW,
		WHITE_CROSS,
		RED_POINTER,
		WHITE_CIRCLE,
		SMALL_WHITE_CIRCLE,
		MANSION,
		TEMPLE,
		WHITE_BANNER,
		ORANGE_BANNER,
		MAGENTA_BANNER,
		LIGHT_BLUE_BANNER,
		YELLOW_BANNER,
		LIME_BANNER,
		PINK_BANNER,
		GRAY_BANNER,
		LIGHT_GRAY_BANNER,
		CYAN_BANNER,
		PURPLE_BANNER,
		BLUE_BANNER,
		BROWN_BANNER,
		GREEN_BANNER,
		RED_BANNER,
		BLACK_BANNER,
		TREASURE_MARKER;
		
		private static List<IconType> VALUES;
		
		@Override
		public int getID() {
			return ordinal();
		}
		
		public static IconType getByID(int id) {
			return getValues().get(id);
		}
		
		/**
		 * Returns a immutable List of all Types.<br>
		 * This method avoid allocation of a new array not like {@link #values()}.
		 * @return list
		 */
		public static List<IconType> getValues() {
			if (VALUES == null)
				VALUES = List.of(values());
			return VALUES;
		}
		
		/**
		 * Releases the system resources used from the values cache
		 */
		public static void freeValues() {
			VALUES = null;
		}
		
	}

	public IconType getType() {
		return type;
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
	}

	public float getDirection() {
		return direction;
	}

	public Chat getDisplayName() {
		return name;
	}

	public void setType(IconType type) {
		this.type = type;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void setDirection(float direction) {
		this.direction = direction;
	}

	public void setDisplayName(Chat name) {
		this.name = name;
	}
	
	public MapIcon clone() {
		try {
			return (MapIcon) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean hasDisplayName() {
		return name != null;
	}

}
