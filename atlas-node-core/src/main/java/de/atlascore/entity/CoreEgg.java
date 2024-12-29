package de.atlascore.entity;

import java.util.UUID;

import de.atlasmc.entity.Egg;
import de.atlasmc.entity.EntityType;

public class CoreEgg extends CoreThrowableProjectile implements Egg {

	public CoreEgg(EntityType type, UUID uuid) {
		super(type, uuid);
	}

}
