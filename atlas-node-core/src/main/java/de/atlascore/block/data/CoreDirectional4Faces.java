package de.atlascore.block.data;

import java.util.Set;

import de.atlasmc.Material;
import de.atlasmc.block.BlockFace;
import de.atlasmc.block.data.Directional;

/**
 * {@link Directional} implementation that accepts the 4 faces NORTH, SOUTH, EAST and WEST
 */
public class CoreDirectional4Faces extends CoreAbstractDirectional {
	
	private static final Set<BlockFace> ALLOWED_FACES =
			Set.of(BlockFace.NORTH,
					BlockFace.SOUTH,
					BlockFace.EAST,
					BlockFace.WEST);
	
	public CoreDirectional4Faces(Material material) {
		super(material);
	}
	
	public CoreDirectional4Faces(Material material, BlockFace face) {
		super(material, face);
	}

	@Override
	public Set<BlockFace> getFaces() {
		return ALLOWED_FACES;
	}

	@Override
	protected int getFaceValue(BlockFace face) {
		switch (getFacing()) {
		case NORTH: return 0;
		case EAST: return 3;
		case SOUTH: return 1;
		case WEST: return 2;
		default:
			return -1;
		}
	}
	
	@Override
	public int getStateID() {
		return getMaterial().getBlockStateID()+getFaceValue();
	}

}
