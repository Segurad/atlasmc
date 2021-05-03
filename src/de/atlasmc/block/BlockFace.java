package de.atlasmc.block;

public enum BlockFace {
	NORTH,
	EAST,
	SOUTH,
	WEST,
	UP,
	DOWN,
	NORTH_EAST,
	NORTH_WEST,
	SOUTH_EAST,
	SOUTH_WEST,
	WEST_NORTH_WEST,
	NORTH_NORTH_WEST,
	NORTH_NORTH_EAST,
	EAST_NORTH_EAST,
	EAST_SOUTH_EAST,
	SOUTH_SOUTH_EAST,
	SOUTH_SOUTH_WEST,
	WEST_SOUTH_WEST,;

	public static BlockFace getByName(String name) {
		BlockFace[] faces = values();
		for (BlockFace face : faces) {
			if (face.name().equalsIgnoreCase(name)) {
				return face;
			};
		}
		return null;
	}

}
