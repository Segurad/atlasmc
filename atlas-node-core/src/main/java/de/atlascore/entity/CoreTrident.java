package de.atlascore.entity;

import java.io.IOException;
import java.util.UUID;

import de.atlasmc.Material;
import de.atlasmc.entity.EntityType;
import de.atlasmc.entity.Trident;
import de.atlasmc.entity.data.MetaDataField;
import de.atlasmc.entity.data.MetaDataType;
import de.atlasmc.inventory.ItemStack;
import de.atlasmc.util.map.key.CharKey;
import de.atlasmc.util.nbt.io.NBTWriter;

public class CoreTrident extends CoreAbstractArrow implements Trident {

	protected static final MetaDataField<Integer>
	META_LOYALITY_LEVEL = new MetaDataField<>(CoreAbstractArrow.LAST_META_INDEX+1, 0, MetaDataType.VAR_INT);
	protected static final MetaDataField<Boolean>
	META_ENCHANTMENT_GLINT = new MetaDataField<>(CoreAbstractArrow.LAST_META_INDEX+2, false, MetaDataType.BOOLEAN);
	
	protected static final int LAST_META_INDEX = CoreAbstractArrow.LAST_META_INDEX+2;
	
	protected static final CharKey
	NBT_TRIDENT = CharKey.literal("Trident");
	
	static {
		NBT_FIELDS.setField(NBT_TRIDENT, (holder, reader) -> {
			if (holder instanceof Trident) {
				reader.readNextEntry();
				Material mat = null;
				if (!NBT_ID.equals(reader.getFieldName())) {
					reader.mark();
					reader.search(NBT_ID);
					mat = Material.getByName(reader.readStringTag());
					reader.reset();
				} else mat = Material.getByName(reader.readStringTag());
				ItemStack item = new ItemStack(mat);
				item.fromNBT(reader);
				((Trident) holder).setItem(item);
			} else reader.skipTag();
		});
	}
	
	private ItemStack item;
	
	public CoreTrident(EntityType type, UUID uuid) {
		super(type, uuid);
	}
	
	@Override
	protected void initMetaContainer() {
		super.initMetaContainer();
		metaContainer.set(META_LOYALITY_LEVEL);
		metaContainer.set(META_ENCHANTMENT_GLINT);
	}
	
	@Override
	protected int getMetaContainerSize() {
		return LAST_META_INDEX+1;
	}

	@Override
	public int getLoyalityLevel() {
		return metaContainer.getData(META_LOYALITY_LEVEL);
	}

	@Override
	public boolean hasEnchantmentGlint() {
		return metaContainer.getData(META_ENCHANTMENT_GLINT);
	}

	@Override
	public void setLoyalityLevel(int level) {
		metaContainer.get(META_LOYALITY_LEVEL).setData(level);
	}

	@Override
	public void setEnchantmentGlint(boolean glint) {
		metaContainer.get(META_ENCHANTMENT_GLINT).setData(glint);
	}

	@Override
	public void setItem(ItemStack item) {
		this.item = item;
	}

	@Override
	public ItemStack getItem() {
		return item;
	}

	@Override
	public boolean hasItem() {
		return item != null;
	}

	@Override
	public void toNBT(NBTWriter writer, boolean systemData) throws IOException {
		super.toNBT(writer, systemData);
		if (hasItem()) {
			writer.writeCompoundTag(NBT_TRIDENT);
			getItem().toNBT(writer, systemData);
			writer.writeEndTag();
		}
	}
	
}
