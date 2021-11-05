package de.atlasmc.inventory.gui.component;

import de.atlasmc.inventory.ItemStack;
import de.atlasmc.inventory.gui.GUI;

public class ItemComponent extends AbstractComponent<ItemStack> {

	public ItemComponent(int x, int y) {
		super(x, y);
	}

	@Override
	public ItemComponentHandler createHandler(GUI gui, int slot, int length, int depth, int offsetX, int offsetY) {
		return createHandler(gui, slot, length, depth, offsetX, offsetY, true);
	}
	
	public ItemComponentHandler createHandler(GUI gui, int slot, int length, int depth, int offsetX, int offsetY, boolean slotwatcher) {
		ItemComponentHandler h = new ItemComponentHandler(this, gui, slot, length, depth, offsetX, offsetY, slotwatcher);
		handlers.add(h);
		return h;
	}

	/**
	 * 
	 * @param item
	 * @return return the ItemStack with the remaining amount or null
	 */
	public ItemStack addSafe(ItemStack item) {
		for (int i = 0; i < entries.length; i++) {
			if (entries[i] != null) {
				ItemStack entry = (ItemStack) entries[i];
				if (entry.isSimilar(item) && entry.getAmount() < entry.getMaxStackSize()) {
					if (entry.getAmount() + item.getAmount() > entry.getMaxStackSize()) {
						item.setAmount(item.getAmount() - (entry.getMaxStackSize()-entry.getAmount()));
						entry.setAmount(entry.getMaxStackSize());
					} else {
						entry.setAmount(entry.getAmount()+item.getAmount());
						return null;
					}
					if (!handlers.isEmpty()) for (ComponentHandler h : handlers) {
						h.internalUpdate(x, y);
					};
				}
				continue;
			}
			entries[i] = item;
			if (!handlers.isEmpty()) for (ComponentHandler h : handlers) {
				h.internalUpdate(x, y);
			};
			return null;
		}
		return item;
	}

}
