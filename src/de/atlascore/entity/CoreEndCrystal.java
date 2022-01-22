package de.atlascore.entity;

import java.util.UUID;

import de.atlasmc.SimpleLocation;
import de.atlasmc.entity.EndCrystal;
import de.atlasmc.entity.EntityType;
import de.atlasmc.entity.data.MetaDataField;
import de.atlasmc.entity.data.MetaDataType;
import de.atlasmc.util.MathUtil;
import de.atlasmc.world.World;

public class CoreEndCrystal extends CoreEntity implements EndCrystal {
	
	protected static final MetaDataField<Long>
	META_BEAM_TARGET = new MetaDataField<>(CoreEntity.LAST_META_INDEX+1, null, MetaDataType.OPT_POSITION);
	protected static final MetaDataField<Boolean>
	META_SHOW_BOTTOM = new MetaDataField<>(CoreEntity.LAST_META_INDEX+2, true, MetaDataType.BOOLEAN);
	
	protected static final int LAST_META_INDEX = CoreEntity.LAST_META_INDEX+2;
	
	public CoreEndCrystal(EntityType type, UUID uuid, World world) {
		super(type, uuid, world);
	}
	
	@Override
	protected void initMetaContainer() {
		super.initMetaContainer();
		metaContainer.set(META_BEAM_TARGET);
		metaContainer.set(META_SHOW_BOTTOM);
	}
	
	@Override
	protected int getMetaContainerSize() {
		return LAST_META_INDEX+1;
	}

	@Override
	public SimpleLocation getBeamTarget() {
		return hasTarget() ? getBeamTarget(new SimpleLocation()) : null;
	}

	@Override
	public SimpleLocation getBeamTarget(SimpleLocation loc) {
		return hasTarget() ? MathUtil.getLocation(loc, metaContainer.getData(META_BEAM_TARGET)) : null;
	}

	@Override
	public void setBeamTarget(SimpleLocation loc) {
		if (loc == null) {
			resetTarget();
			return;
		}
		metaContainer.get(META_BEAM_TARGET).setData(MathUtil.toPosition(loc));
	}

	@Override
	public void setBeamTarget(int x, int y, int z) {
		metaContainer.get(META_BEAM_TARGET).setData(MathUtil.toPosition(x, y, z));
	}

	@Override
	public boolean getShowBottom() {
		return metaContainer.getData(META_SHOW_BOTTOM);
	}

	@Override
	public void setShowBottom(boolean show) {
		metaContainer.get(META_SHOW_BOTTOM).setData(show);		
	}

	@Override
	public boolean hasTarget() {
		return metaContainer.getData(META_BEAM_TARGET) != null;
	}

	@Override
	public void resetTarget() {
		metaContainer.get(META_BEAM_TARGET).setData(null);
	}

}
