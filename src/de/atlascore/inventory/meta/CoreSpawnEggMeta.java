package de.atlascore.inventory.meta;

import de.atlasmc.Material;
import de.atlasmc.entity.EntityType;
import de.atlasmc.inventory.meta.SpawnEggMeta;
import de.atlasmc.util.Validate;

public class CoreSpawnEggMeta extends CoreItemMeta implements SpawnEggMeta {

	private EntityType type;
	
	public CoreSpawnEggMeta(Material material) {
		super(material);
	}

	@Override
	public CoreSpawnEggMeta clone() {
		return (CoreSpawnEggMeta) super.clone();
	}

	@Override
	public EntityType getSpawnedType() {
		return type;
	}

	@Override
	public void setSpawnedType(EntityType type) {
		Validate.notNull(type, "EntityType can not be null!");
		this.type = type;
	}

}