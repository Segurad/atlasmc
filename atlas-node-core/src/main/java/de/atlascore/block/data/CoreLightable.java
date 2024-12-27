package de.atlascore.block.data;

import java.util.List;

import de.atlasmc.Material;
import de.atlasmc.block.data.Lightable;
import de.atlasmc.block.data.property.BlockDataProperty;

public class CoreLightable extends CoreBlockData implements Lightable {

	protected static final List<BlockDataProperty<?>> PROPERTIES;
	
	static {
		PROPERTIES = merge(CoreBlockData.PROPERTIES, BlockDataProperty.LIT);
	}
	
	protected boolean lit;
	
	public CoreLightable(Material material) {
		super(material);
	}

	@Override
	public boolean isLit() {
		return lit;
	}

	@Override
	public void setLit(boolean lit) {
		this.lit = lit;
	}
	
	@Override
	public int getStateID() {
		return material.getBlockStateID()+(lit?0:1);
	}
	
	@Override
	public List<BlockDataProperty<?>> getProperties() {
		return PROPERTIES;
	}
	
}
