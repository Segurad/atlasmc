package de.atlascore.entity;

import java.util.UUID;

import de.atlasmc.entity.EntityType;
import de.atlasmc.entity.Slime;
import de.atlasmc.world.World;

public class CoreSlime extends CoreAbstractSlime implements Slime {
	
	public CoreSlime(EntityType type, UUID uuid, World world) {
		super(type, uuid, world);
	}

}
