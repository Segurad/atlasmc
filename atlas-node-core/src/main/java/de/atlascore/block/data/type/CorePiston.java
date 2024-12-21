package de.atlascore.block.data.type;

import java.util.List;

import de.atlascore.block.data.CoreDirectional6Faces;
import de.atlasmc.Material;
import de.atlasmc.block.data.property.BlockDataProperty;
import de.atlasmc.block.data.type.Piston;

public class CorePiston extends CoreDirectional6Faces implements Piston {

	protected static final List<BlockDataProperty<?>> PROPERTIES;
	
	static {
		PROPERTIES = merge(CoreDirectional6Faces.PROPERTIES, BlockDataProperty.EXTENDED);
	}
	
	private boolean extended;
	
	public CorePiston(Material material) {
		super(material);
	}

	@Override
	public boolean isExtended() {
		return extended;
	}

	@Override
	public void setExtended(boolean extended) {
		this.extended = extended;
	}

	@Override
	public int getStateID() {
		return getMaterial().getBlockStateID()+getFaceValue()+(extended?0:6);
	}
	
	@Override
	public List<BlockDataProperty<?>> getProperties() {
		return PROPERTIES;
	}

}
