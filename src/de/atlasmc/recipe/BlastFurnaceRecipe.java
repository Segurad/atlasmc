package de.atlasmc.recipe;

import de.atlasmc.NamespacedKey;

public class BlastFurnaceRecipe extends AbstractCookingRecipe {

	public BlastFurnaceRecipe(NamespacedKey key, NamespacedKey group) {
		super(key, group);
	}

	@Override
	public RecipeType getType() {
		return RecipeType.BLASTING;
	}

}
