package de.atlasmc.inventory.gui;

import java.util.List;

import de.atlasmc.entity.Player;
import de.atlasmc.event.inventory.InventoryClickEvent;
import de.atlasmc.inventory.Inventory;
import de.atlasmc.inventory.ItemStack;
import de.atlasmc.inventory.gui.component.Component;
import de.atlasmc.inventory.gui.component.ComponentHandler;

public interface GUI extends Inventory {

	public void open(Player player);

	public Button getButton(int slot);
	public boolean isButtonAt(int slot);
	public void setButton(int slot, Button button);
	public void setButton(int slot, Button button, boolean icon);
	public void setButtons(int start, int end, Button button, boolean icon);
	
	public ItemStack getItem(int slot);
	public void setItem(int slot, ItemStack item);

	public void setClickable(boolean value, int... slot);
	public boolean isClickable(int slot);

	public int getSize();
	public void clearButtons();
	
	public ComponentHandler addComponent(Component<?> component, int slot, int length, int depth);
	public void addComponentHandler(ComponentHandler handler);
	public List<ComponentHandler> getComponents();
	public void removeComponentHandler(ComponentHandler handler);

	public void notifyClosedBy(Player player);
	public void notifyOpenedBy(Player player);
	public void notifyClick(InventoryClickEvent event);
	
	public void addGUIListener(GUIListener listener);
	public List<GUIListener> getGUIListeners();
	public void removeGUIListener(GUIListener listener);
}
