package de.atlascore.entity;

import java.util.UUID;

import de.atlasmc.entity.EntityType;
import de.atlasmc.entity.LightningBolt;
import de.atlasmc.world.World;

public class CoreLightningBolt extends CoreEntity implements LightningBolt {

	public CoreLightningBolt(EntityType type, UUID uuid, World world) {
		super(type, uuid, world);
	}

}
