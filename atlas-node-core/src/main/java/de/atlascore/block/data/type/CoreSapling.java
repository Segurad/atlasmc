package de.atlascore.block.data.type;

import java.util.List;

import de.atlascore.block.data.CoreBlockData;
import de.atlasmc.Material;
import de.atlasmc.block.data.property.BlockDataProperty;
import de.atlasmc.block.data.type.Sapling;

public class CoreSapling extends CoreBlockData implements Sapling {

	protected static final List<BlockDataProperty<?>> PROPERTIES;
	
	static {
		PROPERTIES = merge(CoreBlockData.PROPERTIES, BlockDataProperty.STAGE);
	}
	
	private int stage;
	private int maxstage;
	
	public CoreSapling(Material material) {
		this(material, 1);
	}
	
	public CoreSapling(Material material, int maxstage) {
		super(material);
		this.maxstage = maxstage;
	}

	@Override
	public int getMaxStage() {
		return maxstage;
	}

	@Override
	public int getStage() {
		return stage;
	}

	@Override
	public void setStage(int stage) {
		if (maxstage < stage || stage < 0) 
			throw new IllegalArgumentException("Stage is not between 0 and " + maxstage + ": " + stage);
		this.stage = stage;
	}

	@Override
	public int getStateID() {
		return super.getStateID() + stage;
	}
	
	@Override
	public List<BlockDataProperty<?>> getProperties() {
		return PROPERTIES;
	}
	
}
