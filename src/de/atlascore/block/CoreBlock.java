package de.atlascore.block;

import de.atlasmc.Location;
import de.atlasmc.Material;
import de.atlasmc.block.Block;
import de.atlasmc.block.data.BlockData;
import de.atlasmc.world.Chunk;
import de.atlasmc.world.EnumBiome;
import de.atlasmc.world.World;

public class CoreBlock implements Block {
	
	private final Location loc;
	private final Chunk chunk;
	
	public CoreBlock(Location loc, Chunk chunk) {
		this.loc = loc;
		this.chunk = chunk;
	}
	
	@Override
	public Material getType() {
		return chunk.getBlockType(loc.getBlockX() & 0xF, loc.getBlockY(), loc.getBlockZ() & 0xF);
	}

	@Override
	public void setBlockData(BlockData data) {
		this.chunk.setBlockDataAt(data, loc.getBlockX() & 0xF, loc.getBlockY(), loc.getBlockZ() & 0xF);
	}

	@Override
	public void setType(Material material) {
		if (!material.isBlock()) throw new IllegalArgumentException("Material is not a Block: " + material.getName());
		chunk.setBlockType(material, loc.getBlockX() & 0xF, loc.getBlockY(), loc.getBlockZ() & 0xF);
	}

	@Override
	public BlockData getBlockData() {
		return chunk.getBlockDataAt(loc.getBlockX() & 0xF, loc.getBlockY(), loc.getBlockZ() & 0xF);
	}

	@Override
	public int getY() {
		return loc.getBlockY();
	}

	@Override
	public EnumBiome getBiome() {
		return chunk.getBiome(loc.getBlockX() & 0xF, loc.getBlockY(), loc.getBlockZ() & 0xF);
	}

	@Override
	public World getWorld() {
		return loc.getWorld();
	}

	@Override
	public int getX() {
		return loc.getBlockX();
	}

	@Override
	public int getZ() {
		return loc.getBlockZ();
	}

}
