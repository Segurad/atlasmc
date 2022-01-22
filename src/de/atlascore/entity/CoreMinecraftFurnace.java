package de.atlascore.entity;

import java.util.UUID;

import de.atlasmc.entity.EntityType;
import de.atlasmc.entity.MinecartFurnace;
import de.atlasmc.entity.data.MetaDataField;
import de.atlasmc.entity.data.MetaDataType;
import de.atlasmc.world.World;

public class CoreMinecraftFurnace extends CoreAbstractMinecart implements MinecartFurnace {

	protected static final MetaDataField<Boolean>
	META_HAS_FUEL = new MetaDataField<>(CoreAbstractMinecart.LAST_META_INDEX+1, false, MetaDataType.BOOLEAN);
	
	protected static final int LAST_META_INDEX = CoreAbstractMinecart.LAST_META_INDEX+1;
	
	public CoreMinecraftFurnace(EntityType type, UUID uuid, World world) {
		super(type, uuid, world);
	}
	
	@Override
	protected void initMetaContainer() {
		super.initMetaContainer();
		metaContainer.set(META_HAS_FUEL);
	}
	
	@Override
	protected int getMetaContainerSize() {
		return LAST_META_INDEX+1;
	}

	@Override
	public boolean hasFuel() {
		return metaContainer.getData(META_HAS_FUEL);
	}

	@Override
	public void setFuel(boolean fuel) {
		metaContainer.get(META_HAS_FUEL).setData(fuel);		
	}

}
