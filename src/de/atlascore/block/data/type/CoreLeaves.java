package de.atlascore.block.data.type;

import de.atlascore.block.data.CoreBlockData;
import de.atlasmc.Material;
import de.atlasmc.block.data.type.Leaves;
import de.atlasmc.util.Validate;

public class CoreLeaves extends CoreBlockData implements Leaves {

	private int distance;
	private boolean persistent;
	
	public CoreLeaves(Material material) {
		super(material);
		distance = 7;
	}

	@Override
	public int getDistance() {
		return distance;
	}

	@Override
	public boolean isPersistent() {
		return persistent;
	}

	@Override
	public void setDistance(int distance) {
		Validate.isTrue(distance > 0 && distance < 8, "Distance is not between 1 and 7: " + distance);
		this.distance = distance;
	}

	@Override
	public void setPersistent(boolean persistent) {
		this.persistent = persistent;
	}
	
	@Override
	public int getStateID() {
		return super.getStateID() + (distance-1)*2+(persistent?0:1);
	}

}