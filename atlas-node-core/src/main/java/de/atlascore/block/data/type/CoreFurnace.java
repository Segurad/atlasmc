package de.atlascore.block.data.type;

import java.util.List;

import de.atlascore.block.data.CoreDirectional4Faces;
import de.atlasmc.Material;
import de.atlasmc.block.data.property.BlockDataProperty;
import de.atlasmc.block.data.type.Furnace;

public class CoreFurnace extends CoreDirectional4Faces implements Furnace {

	protected static final List<BlockDataProperty<?>> PROPERTIES;
	
	static {
		PROPERTIES = merge(CoreDirectional4Faces.PROPERTIES, BlockDataProperty.LIT);
	}
	
	private boolean lit;
	
	public CoreFurnace(Material material) {
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
		return getMaterial().getBlockStateID()+
				(lit?0:1)+
				getFaceValue()*2;
	}
	
	@Override
	public List<BlockDataProperty<?>> getProperties() {
		return PROPERTIES;
	}

}
