package de.atlascore.inventory;

import de.atlasmc.event.inventory.InventoryType;
import de.atlasmc.inventory.InventoryHolder;
import de.atlasmc.inventory.ItemStack;
import de.atlasmc.inventory.SmithingInventory;

public class CoreSmithingInventory extends CoreInventory implements SmithingInventory {

	public CoreSmithingInventory(String title, InventoryHolder holder) {
		super(3, InventoryType.SMITHING, title, holder);
	}

	@Override
	public ItemStack getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResult(ItemStack item) {
		// TODO Auto-generated method stub
		
	}

}
