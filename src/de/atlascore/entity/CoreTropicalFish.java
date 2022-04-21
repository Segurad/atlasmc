package de.atlascore.entity;

import java.io.IOException;
import java.util.UUID;

import de.atlasmc.DyeColor;
import de.atlasmc.entity.EntityType;
import de.atlasmc.entity.TropicalFish;
import de.atlasmc.entity.data.MetaData;
import de.atlasmc.entity.data.MetaDataField;
import de.atlasmc.entity.data.MetaDataType;
import de.atlasmc.util.nbt.ChildNBTFieldContainer;
import de.atlasmc.util.nbt.NBTFieldContainer;
import de.atlasmc.util.nbt.io.NBTWriter;
import de.atlasmc.world.World;

public class CoreTropicalFish extends CoreFish implements TropicalFish {

	/**
	 * 0xFF000000 - Pattern Color<br>
	 * 0x00FF0000 - Base Color<br>
	 * 0x0000FF00 - Pattern<br>
	 * 0x000000FF - Size<br>
	 */
	protected static final MetaDataField<Integer>
	META_TROPICAL_VARIANT = new MetaDataField<>(CoreFish.LAST_META_INDEX+1, 0, MetaDataType.INT);
	
	protected static final int LAST_META_INDEX = CoreFish.LAST_META_INDEX+1;
	
	protected static final NBTFieldContainer NBT_FIELDS;
	
	protected static final String
	NBT_VARIANT = "Variant";
	
	static {
		NBT_FIELDS = new ChildNBTFieldContainer(CoreFish.NBT_FIELDS);
		NBT_FIELDS.setField(NBT_VARIANT, (holder, reader) -> {
			if (holder instanceof TropicalFish) {
				int variant = reader.readIntTag();
				TropicalFish fish = (TropicalFish) holder;
				fish.setPattern(Pattern.getByDataID(variant));
				fish.setBaseColor(DyeColor.getByID(variant >> 16 & 0xFF));
				fish.setPatternColor(DyeColor.getByID(variant >> 24 & 0xFF));
			} else reader.skipTag();
		});
	}
	
	public CoreTropicalFish(EntityType type, UUID uuid, World world) {
		super(type, uuid, world);
	}
	
	@Override
	protected NBTFieldContainer getFieldContainerRoot() {
		return NBT_FIELDS;
	}
	
	@Override
	protected void initMetaContainer() {
		super.initMetaContainer();
		metaContainer.set(META_TROPICAL_VARIANT);
	}
	
	@Override
	protected int getMetaContainerSize() {
		return LAST_META_INDEX+1;
	}

	@Override
	public Pattern getPattern() {
		return Pattern.getByDataID(metaContainer.getData(META_TROPICAL_VARIANT));
	}

	@Override
	public void setPattern(Pattern pattern) {
		if (pattern == null)
			throw new IllegalArgumentException("Pattern can not be null!");
		MetaData<Integer> data = metaContainer.get(META_TROPICAL_VARIANT);
		data.setData((data.getData() & 0xFFFF) | pattern.getDataID());
	}

	@Override
	public DyeColor getBaseColor() {
		return DyeColor.getByID((metaContainer.getData(META_TROPICAL_VARIANT) >> 16) & 0xFF);
	}

	@Override
	public void setBaseColor(DyeColor color) {
		if (color == null)
			throw new IllegalArgumentException("Color can not be null");
		MetaData<Integer> data = metaContainer.get(META_TROPICAL_VARIANT);
		data.setData((data.getData() & 0xFF0000) | (color.getID() << 16));
	}

	@Override
	public DyeColor getPatternColor() {
		return DyeColor.getByID((metaContainer.getData(META_TROPICAL_VARIANT) >> 24) & 0xFF);
	}

	@Override
	public void setPatternColor(DyeColor color) {
		if (color == null)
			throw new IllegalArgumentException("Color can not be null");
		MetaData<Integer> data = metaContainer.get(META_TROPICAL_VARIANT);
		data.setData((data.getData() & 0xFF000000) | (color.getID() << 24));
	}
	
	@Override
	public void toNBT(NBTWriter writer, boolean systemData) throws IOException {
		super.toNBT(writer, systemData);
		writer.writeIntTag(NBT_VARIANT, metaContainer.getData(META_TROPICAL_VARIANT));
	}

}
