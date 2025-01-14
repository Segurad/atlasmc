package de.atlascore.entity;

import java.io.IOException;
import java.util.UUID;

import de.atlasmc.entity.EntityType;
import de.atlasmc.entity.Zoglin;
import de.atlasmc.entity.data.MetaDataField;
import de.atlasmc.entity.data.MetaDataType;
import de.atlasmc.util.map.key.CharKey;
import de.atlasmc.util.nbt.NBTFieldSet;
import de.atlasmc.util.nbt.io.NBTWriter;

public class CoreZoglin extends CoreMob implements Zoglin {

	protected static final MetaDataField<Boolean>
	META_IS_BABY = new MetaDataField<>(CoreMob.LAST_META_INDEX + 1, false, MetaDataType.BOOLEAN);
	
	protected static final int LAST_META_INDEX = CoreMob.LAST_META_INDEX + 1;
	
	protected static final NBTFieldSet<CoreZoglin> NBT_FIELDS;
	
	protected static final CharKey
	NBT_IS_BABY = CharKey.literal("IsBaby");
	
	static {
		NBT_FIELDS = CoreMob.NBT_FIELDS.fork();
		NBT_FIELDS.setField(NBT_IS_BABY, (holder, reader) -> {
			holder.setBaby(reader.readByteTag() == 1);
		});
	}
	
	public CoreZoglin(EntityType type, UUID uuid) {
		super(type, uuid);
	}
	
	@Override
	protected NBTFieldSet<? extends CoreZoglin> getFieldSetRoot() {
		return NBT_FIELDS;
	}
	
	@Override
	protected void initMetaContainer() {
		super.initMetaContainer();
		metaContainer.set(META_IS_BABY);
	}
	
	@Override
	protected int getMetaContainerSize() {
		return LAST_META_INDEX + 1;
	}

	@Override
	public boolean isBaby() {
		return metaContainer.getData(META_IS_BABY);
	}

	@Override
	public void setBaby(boolean baby) {
		metaContainer.get(META_IS_BABY).setData(baby);
	}
	
	@Override
	public void toNBT(NBTWriter writer, boolean systemData) throws IOException {
		super.toNBT(writer, systemData);
		if (isBaby())
			writer.writeByteTag(NBT_IS_BABY, true);
	}

}
