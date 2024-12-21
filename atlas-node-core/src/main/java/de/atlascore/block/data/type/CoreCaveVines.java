package de.atlascore.block.data.type;

import java.util.List;

import de.atlascore.block.data.CoreAgeable;
import de.atlasmc.Material;
import de.atlasmc.block.data.property.BlockDataProperty;
import de.atlasmc.block.data.type.CaveVines;

public class CoreCaveVines extends CoreAgeable implements CaveVines {

	protected static final List<BlockDataProperty<?>> PROPERTIES;
	
	static {
		PROPERTIES = merge(CoreAgeable.PROPERTIES, BlockDataProperty.BERRIES);
	}
	
	private boolean berries;
	
	public CoreCaveVines(Material material) {
		super(material, 25);
	}

	@Override
	public boolean hasBerries() {
		return berries;
	}

	@Override
	public void setBerries(boolean berries) {
		this.berries = berries;
	}
	
	@Override
	public int getStateID() {
		return getMaterial().getBlockStateID()+(berries?0:1)+getAge()*2;
	}
	
	@Override
	public List<BlockDataProperty<?>> getProperties() {
		return PROPERTIES;
	}

}
