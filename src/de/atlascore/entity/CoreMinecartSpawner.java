package de.atlascore.entity;

import java.util.UUID;

import de.atlasmc.entity.EntityType;
import de.atlasmc.entity.MinecartSpawner;

public class CoreMinecartSpawner extends CoreAbstractMinecart implements MinecartSpawner {

	public CoreMinecartSpawner(EntityType type, UUID uuid) {
		super(type, uuid);
	}

}
