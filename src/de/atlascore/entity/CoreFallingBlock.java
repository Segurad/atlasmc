package de.atlascore.entity;

import de.atlasmc.Material;
import de.atlasmc.block.data.BlockData;
import de.atlasmc.block.tile.TileEntity;
import de.atlasmc.entity.EntityType;
import de.atlasmc.entity.FallingBlock;
import de.atlasmc.entity.data.MetaDataField;
import de.atlasmc.entity.data.MetaDataType;
import de.atlasmc.util.nbt.ChildNBTFieldContainer;
import de.atlasmc.util.nbt.NBTFieldContainer;
import de.atlasmc.util.nbt.TagType;
import de.atlasmc.util.nbt.io.NBTWriter;
import de.atlasmc.world.World;

import java.io.IOException;
import java.util.UUID;

public class CoreFallingBlock extends CoreEntity implements FallingBlock {
	
	protected static final MetaDataField<Long> 
	META_SPAWN_POS = new MetaDataField<>(CoreEntity.LAST_META_INDEX+1, 0L, MetaDataType.POSISTION);
	
	protected static final int LAST_META_INDEX = CoreEntity.LAST_META_INDEX+1;
	
	protected static final NBTFieldContainer NBT_FIELDS;
	
	protected static final String
	NBT_BLOCK_STATE = "BlockState",
	NBT_NAME = "Name",
	NBT_PROPERTIES = "Properties",
	NBT_DROP_ITEM = "DropItem",
	NBT_FALL_HURT_AMOUNT = "FallHurtAmount",
	NBT_FALL_HURT_MAX = "FallHurtMax",
	NBT_HURT_ENTITIES = "HurtEntities",
	NBT_TILE_ENTITY_DATA = "TileEntityData";
	// TODO unused NBT_TIME = "Time";
	
	static {
		NBT_FIELDS = new ChildNBTFieldContainer(CoreEntity.NBT_FIELDS);
		NBT_FIELDS.setField(NBT_BLOCK_STATE, (holder, reader) -> {
			if (!(holder instanceof FallingBlock)) {
				reader.skipTag();
				return;
			}
			reader.readNextEntry();
			Material mat = null;
			BlockData data = null;
			while (reader.getType() != TagType.TAG_END) {
				switch (reader.getFieldName()) {
				case NBT_NAME:
					mat = Material.getByName(reader.readStringTag());
					break;
				case NBT_PROPERTIES:
					if (mat == null)
						reader.skipTag();
					else {
						data = mat.createBlockData();
						reader.readNextEntry();
						data.fromNBT(reader);
					}
					break;
				default:
					reader.skipTag();
					break;
				}
			}
			reader.skipTag();
			if (data != null)
				((FallingBlock) holder).setBlockData(data);
			else if (mat != null)
				((FallingBlock) holder).setBlockDataType(mat);
		});
		NBT_FIELDS.setField(NBT_DROP_ITEM, (holder, reader) -> {
			if (holder instanceof FallingBlock) {
				((FallingBlock) holder).setDropItems(reader.readByteTag() == 1);
			} else reader.skipTag();
		});
		NBT_FIELDS.setField(NBT_FALL_HURT_AMOUNT, (holder, reader) -> {
			if (holder instanceof FallingBlock) {
				((FallingBlock) holder).setBaseDamage(reader.readFloatTag());
			} else reader.skipTag();
		});
		NBT_FIELDS.setField(NBT_FALL_HURT_MAX, (holder, reader) -> {
			if (holder instanceof FallingBlock) {
				((FallingBlock) holder).setMaxDamage(reader.readIntTag());
			} else reader.skipTag();
		});
		NBT_FIELDS.setField(NBT_HURT_ENTITIES, (holder, reader) -> {
			if (holder instanceof FallingBlock) {
				((FallingBlock) holder).setHurtEntities(reader.readByteTag() == 1);
			} else reader.skipTag();
		});
		NBT_FIELDS.setField(NBT_TILE_ENTITY_DATA, (holder, reader) -> {
			if (!(holder instanceof FallingBlock)) {
				reader.skipTag();
				return;
			}
			reader.readNextEntry();
			Material mat = null;
			if (!NBT_ID.equals(reader.getFieldName())) {
				reader.mark();
				reader.search(NBT_ID);
				mat = Material.getByName(reader.readStringTag());
				reader.reset();
			} else mat = Material.getByName(reader.readStringTag());
			TileEntity tile = mat != null ? mat.createTileEntity() : null;
			if (tile == null) {
				while (reader.getType() != TagType.TAG_END)
					reader.skipTag();
				reader.readNextEntry();
			} else tile.fromNBT(reader);
			((FallingBlock) holder).setTileEntity(tile);
		});
	}
	
	private boolean canHurtEntities;
	private boolean canDropItem;
	private float baseDamage;
	private int maxDamage = -1;
	private BlockData data;
	private TileEntity tile;
	
	public CoreFallingBlock(EntityType type, UUID uuid, World world) {
		super(type, uuid, world);
	}
	
	@Override
	protected NBTFieldContainer getFieldContainerRoot() {
		return NBT_FIELDS;
	}

	@Override
	protected void initMetaContainer() {
		super.initMetaContainer();
		metaContainer.set(META_SPAWN_POS);
	}
	
	@Override
	protected int getMetaContainerSize() {
		return LAST_META_INDEX+1;
	}
	
	@Override
	public boolean canHurtEntities() {
		return canHurtEntities;
	}

	@Override
	public BlockData getBlockData() {
		return data;
	}

	@Override
	public boolean canDropItems() {
		return canDropItem;
	}

	@Override
	public Material getMaterial() {
		return data == null ? tile == null ? null : tile.getType() : data.getMaterial();
	}

	@Override
	public void setDropItems(boolean drop) {
		this.canDropItem = drop;
	}

	@Override
	public void setHurtEntities(boolean hurtEntities) {
		this.canHurtEntities = hurtEntities;
	}

	@Override
	public void setBlockData(BlockData data) {
		this.data = data;
	}

	@Override
	public void setBlockDataType(Material mat) {
		if (mat == null)
			this.data = null;
		else
			this.data = mat.createBlockData();
	}

	@Override
	public void setBaseDamage(float damage) {
		this.baseDamage = damage;
	}

	@Override
	public float getBaseDamage() {
		return baseDamage;
	}

	@Override
	public void setMaxDamage(int damage) {
		this.maxDamage = damage;
	}

	@Override
	public int getMaxDamage() {
		return maxDamage;
	}

	@Override
	public void setTileEntity(TileEntity tile) {
		this.tile = tile;
	}

	@Override
	public TileEntity getTileEntity() {
		return tile;
	}

	@Override
	public void toNBT(NBTWriter writer, boolean systemData) throws IOException {
		super.toNBT(writer, systemData);
		if (getBlockData() != null) {
			BlockData data = getBlockData();
			writer.writeCompoundTag(NBT_BLOCK_STATE);
			writer.writeStringTag(NBT_NAME, data.getMaterial().getNamespacedName());
			writer.writeCompoundTag(NBT_PROPERTIES);
			data.toNBT(writer, systemData);
			writer.writeEndTag();
			writer.writeEndTag();
		}
		if (canDropItems())
			writer.writeByteTag(NBT_DROP_ITEM, true);
		writer.writeFloatTag(NBT_FALL_HURT_AMOUNT, getBaseDamage());
		writer.writeIntTag(NBT_FALL_HURT_MAX, getMaxDamage());
		if (canHurtEntities())
			writer.writeByteTag(NBT_HURT_ENTITIES, true);
		if (getTileEntity() != null) {
			writer.writeCompoundTag(NBT_TILE_ENTITY_DATA);
			getTileEntity().toNBT(writer, systemData);
			writer.writeEndTag();
		}
	}
	
}
