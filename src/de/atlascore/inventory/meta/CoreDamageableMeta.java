package de.atlascore.inventory.meta;

import java.io.IOException;

import de.atlasmc.Material;
import de.atlasmc.inventory.meta.DamageableMeta;
import de.atlasmc.inventory.meta.ItemMeta;
import de.atlasmc.util.nbt.io.NBTWriter;

public class CoreDamageableMeta extends CoreItemMeta implements DamageableMeta {

	private int damage;
	
	protected static final String NBT_DAMAGE = "Damage";
	
	static {
		NBT_FIELDS.setField(NBT_DAMAGE, (holder, reader) -> {
			if (holder instanceof DamageableMeta) {
				((DamageableMeta) holder).setDamage(reader.readIntTag());
			} else ((ItemMeta) holder).getCustomTagContainer().addCustomTag(reader.readNBT());
		});
	}
	
	public CoreDamageableMeta(Material material) {
		super(material);
	}

	@Override
	public CoreDamageableMeta clone() {
		return (CoreDamageableMeta) super.clone();
	}

	@Override
	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public boolean hasDamage() {
		return damage > 0;
	}
	
	@Override
	public void toNBT(NBTWriter writer, boolean systemData) throws IOException {
		super.toNBT(writer, systemData);
		writer.writeIntTag(NBT_DAMAGE, damage);
	}
	
	@Override
	public boolean isSimilar(ItemMeta meta, boolean ignoreDamage, boolean checkClass) {
		if (!super.isSimilar(meta, ignoreDamage, checkClass))
			return false;
		if (ignoreDamage)
			return true;
		return getDamage() == ((DamageableMeta) meta).getDamage();
	}

}
