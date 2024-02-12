package de.atlascore.entity;

import java.io.IOException;
import java.util.UUID;

import de.atlasmc.entity.EntityType;
import de.atlasmc.entity.GlowSquid;
import de.atlasmc.util.map.key.CharKey;
import de.atlasmc.util.nbt.ChildNBTFieldContainer;
import de.atlasmc.util.nbt.NBTFieldContainer;
import de.atlasmc.util.nbt.io.NBTWriter;

public class CoreGlowSquid extends CoreSquid implements GlowSquid {

	protected static final NBTFieldContainer<CoreGlowSquid> NBT_FIELDS;
	
	private static final CharKey NBT_DARK_TICKS_REMAINING = CharKey.literal("DarkTicksRemaining");
	
	static {
		NBT_FIELDS = new ChildNBTFieldContainer<>(CoreSquid.NBT_FIELDS);
		NBT_FIELDS.setField(NBT_DARK_TICKS_REMAINING, (holder, reader) -> {
			holder.setDarkTicksRemaining(reader.readIntTag());
		});
	}
	
	private int darkTicksRemaining;
	
	public CoreGlowSquid(EntityType type, UUID uuid) {
		super(type, uuid);
	}
	
	@Override
	protected NBTFieldContainer<? extends CoreLivingEntity> getFieldContainerRoot() {
		return NBT_FIELDS;
	}

	@Override
	public int getDarkTicksRemaining() {
		return darkTicksRemaining;
	}

	@Override
	public void setDarkTicksRemaining(int ticks) {
		this.darkTicksRemaining = Math.max(ticks, 0);
	}
	
	@Override
	public void toNBT(NBTWriter writer, boolean systemData) throws IOException {
		super.toNBT(writer, systemData);
		if (darkTicksRemaining != 0)
			writer.writeIntTag(NBT_DARK_TICKS_REMAINING, darkTicksRemaining);
	}

}
