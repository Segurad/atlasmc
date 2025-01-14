package de.atlascore.block.data.type;

import java.util.List;

import de.atlascore.block.data.CoreDirectional4Faces;
import de.atlasmc.Material;
import de.atlasmc.block.data.property.BlockDataProperty;
import de.atlasmc.block.data.type.Gate;

public class CoreGate extends CoreDirectional4Faces implements Gate {

	protected static final List<BlockDataProperty<?>> PROPERTIES;
	
	static {
		PROPERTIES = merge(CoreDirectional4Faces.PROPERTIES, 
				BlockDataProperty.OPEN,
				BlockDataProperty.POWERED,
				BlockDataProperty.IN_WALL);
	}
	
	private boolean open;
	private boolean powered;
	private boolean inWall;
	
	public CoreGate(Material material) {
		super(material);
	}

	@Override
	public boolean isOpen() {
		return open;
	}

	@Override
	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public boolean isPowered() {
		return powered;
	}

	@Override
	public void setPowered(boolean powered) {
		this.powered = powered;
	}

	@Override
	public boolean isInWall() {
		return inWall;
	}

	@Override
	public void setInWall(boolean inWall) {
		this.inWall = inWall;
	}
	
	@Override
	public int getStateID() {
		return getMaterial().getBlockStateID()+
				(powered?0:1)+
				(open?0:2)+
				(inWall?0:4)+
				getFaceValue()*8;
	}
	
	@Override
	public List<BlockDataProperty<?>> getProperties() {
		return PROPERTIES;
	}

}
