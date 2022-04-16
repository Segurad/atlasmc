package de.atlascore.entity;

import java.io.IOException;
import java.util.UUID;

import de.atlasmc.entity.EntityType;
import de.atlasmc.entity.Zombie;
import de.atlasmc.entity.data.MetaDataField;
import de.atlasmc.entity.data.MetaDataType;
import de.atlasmc.util.nbt.ChildNBTFieldContainer;
import de.atlasmc.util.nbt.NBTFieldContainer;
import de.atlasmc.util.nbt.io.NBTWriter;
import de.atlasmc.world.World;

public class CoreZombie extends CoreMob implements Zombie {

	protected static final MetaDataField<Boolean>
	META_IS_BABY = new MetaDataField<>(CoreMob.LAST_META_INDEX+1, false, MetaDataType.BOOLEAN);
	// META_TYPE (LAST_INDEX+2) is no longer used
	protected static final MetaDataField<Boolean>
	META_IS_BECOMING_DROWNED = new MetaDataField<>(CoreMob.LAST_META_INDEX+3, false, MetaDataType.BOOLEAN);
	
	protected static final int LAST_META_INDEX = CoreMob.LAST_META_INDEX+3;
	
	protected static final NBTFieldContainer NBT_FIELDS;
	
	protected static final String
		NBT_IS_BABY = "IsBaby",
		NBT_CAN_BREAK_DOORS = "CanBreakDoors",
		NBT_DROWNED_CONVERSION_TIME = "DrownedConverionTime";
	
	static {
		NBT_FIELDS = new ChildNBTFieldContainer(CoreMob.NBT_FIELDS);
		NBT_FIELDS.setField(NBT_IS_BABY, (holder, reader) -> {
			if (holder instanceof Zombie) {
				((Zombie) holder).setBaby(reader.readByteTag() == 1);
			} else reader.skipTag();
		});
		NBT_FIELDS.setField(NBT_CAN_BREAK_DOORS, (holder, reader) -> {
			if (holder instanceof Zombie) {
				((Zombie) holder).setCanBreakDoors(reader.readByteTag() == 1);
			} else reader.skipTag();
		});
		NBT_FIELDS.setField(NBT_DROWNED_CONVERSION_TIME, (holder, reader) -> {
			if (holder instanceof Zombie) {
				((Zombie) holder).setDrownedConversionTime(reader.readIntTag());
			} else reader.skipTag();
		});
	}
	
	private boolean canBreakDoor;
	private int drownedConverionTime = -1;
	
	public CoreZombie(EntityType type, UUID uuid, World world) {
		super(type, uuid, world);
	}
	
	@Override
	protected void initMetaContainer() {
		super.initMetaContainer();
		metaContainer.set(META_IS_BABY);
		metaContainer.set(META_IS_BECOMING_DROWNED);
	}
	
	@Override
	protected int getMetaContainerSize() {
		return LAST_META_INDEX+1;
	}

	@Override
	public boolean isBaby() {
		return metaContainer.getData(META_IS_BABY);
	}

	@Override
	public boolean isBecomingDrowned() {
		return metaContainer.getData(META_IS_BECOMING_DROWNED);
	}

	@Override
	public void setBaby(boolean baby) {
		metaContainer.get(META_IS_BABY).setData(baby);
	}

	@Override
	public void setBecomingDorwned(boolean drowned) {
		metaContainer.get(META_IS_BECOMING_DROWNED).setData(drowned);		
	}

	@Override
	public void setCanBreakDoors(boolean breakDoor) {
		this.canBreakDoor = breakDoor;
	}

	@Override
	public boolean canBreakDoors() {
		return canBreakDoor;
	}

	@Override
	public void setDrownedConversionTime(int ticks) {
		this.drownedConverionTime = ticks;
	}

	@Override
	public int getDrownedConverionTime() {
		return drownedConverionTime;
	}
	
	@Override
	public void toNBT(NBTWriter writer, boolean systemData) throws IOException {
		super.toNBT(writer, systemData);
		if (isBaby())
			writer.writeByteTag(NBT_IS_BABY, true);
		if (canBreakDoors())
			writer.writeByteTag(NBT_CAN_BREAK_DOORS, true);
		if (getDrownedConverionTime() > -1)
			writer.writeIntTag(NBT_DROWNED_CONVERSION_TIME, getDrownedConverionTime());
			
	}

}
