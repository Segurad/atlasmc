package de.atlascore.entity;

import java.util.UUID;

import de.atlasmc.entity.ElderGuardian;
import de.atlasmc.entity.EntityType;

public class CoreElderGuardian extends CoreGuardian implements ElderGuardian {

	public CoreElderGuardian(EntityType type, UUID uuid) {
		super(type, uuid);
	}

}
