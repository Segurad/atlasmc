package de.atlasmc.factory;

import de.atlasmc.chat.component.ChatComponent;
import de.atlasmc.event.inventory.InventoryType;
import de.atlasmc.inventory.AnvilInventory;
import de.atlasmc.inventory.BeaconInventory;
import de.atlasmc.inventory.BlastFurnaceInventory;
import de.atlasmc.inventory.BrewingInventory;
import de.atlasmc.inventory.CartographyInventory;
import de.atlasmc.inventory.CraftingInventory;
import de.atlasmc.inventory.EnchantingInventory;
import de.atlasmc.inventory.FurnaceInventory;
import de.atlasmc.inventory.GrindstoneInventory;
import de.atlasmc.inventory.HorseInventory;
import de.atlasmc.inventory.Inventory;
import de.atlasmc.inventory.InventoryHolder;
import de.atlasmc.inventory.LecternInventory;
import de.atlasmc.inventory.LlamaInventory;
import de.atlasmc.inventory.LoomInventory;
import de.atlasmc.inventory.MerchantInventory;
import de.atlasmc.inventory.PlayerInventory;
import de.atlasmc.inventory.SmithingInventory;
import de.atlasmc.inventory.SmokerInventory;
import de.atlasmc.inventory.StonecutterInventory;
import de.atlasmc.inventory.WorkbenchInventory;

/**
 * Factory for creating containers like {@link Inventory}
 */
public abstract class ContainerFactory<I extends Inventory> {
	
	/**
	 * Factory that creates all types of generic inventories<br>
	 * 
	 * List of Types:<br>
	 * {@link InventoryType#GENERIC_9X1},<br>
	 * {@link InventoryType#GENERIC_9X2},<br>
	 * {@link InventoryType#GENERIC_9X3},<br>
	 * {@link InventoryType#GENERIC_9X4},<br>
	 * {@link InventoryType#GENERIC_9X5},<br>
	 * {@link InventoryType#GENERIC_9X6},<br>
	 * {@link InventoryType#GENERIC_3X3},<br>
	 * {@link InventoryType#CHEST},<br>
	 * {@link InventoryType#DOUBLE_CHEST},<br>
	 * {@link InventoryType#ENDER_CHEST},<br>
	 * {@link InventoryType#DISPENSER},<br>
	 * {@link InventoryType#DROPPER},<br>
	 * {@link InventoryType#BARREL},<br>
	 * {@link InventoryType#SHULKER_BOX},<br>
	 * {@link InventoryType#HOPPER}
	 */
	public static ContainerFactory<Inventory> GENERIC_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#BEACON}
	 */
	public static ContainerFactory<BeaconInventory> BEACON_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#PLAYER}
	 */
	public static ContainerFactory<PlayerInventory> PLAYER_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#FURNACE}
	 */
	public static ContainerFactory<FurnaceInventory> FURNACE_INV_FACTPRY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#BREWING}
	 */
	public static ContainerFactory<BrewingInventory> BREWING_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#SMITHING}
	 */
	public static ContainerFactory<SmithingInventory> SMITHING_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#CRAFTING}
	 */
	public static ContainerFactory<CraftingInventory> CRAFTING_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#ANVIL}
	 */
	public static ContainerFactory<AnvilInventory> ANVIL_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#BLAST_FURNACE}
	 */
	public static ContainerFactory<BlastFurnaceInventory> BLAST_FURNACE_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#CARTOGRAPHY}
	 */
	public static ContainerFactory<CartographyInventory> CARTOGRAPHY_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#ENCHANTING}
	 */
	public static ContainerFactory<EnchantingInventory> ENCHANTING_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#GRINDSTONE}
	 */
	public static ContainerFactory<GrindstoneInventory> GRINDSTONE_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#HORSE}
	 */
	public static ContainerFactory<HorseInventory> HORSE_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#LECTERN}
	 */
	public static ContainerFactory<LecternInventory> LECTERN_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#LLAMA}
	 */
	public static ContainerFactory<LlamaInventory> LLAMA_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#MERCHANT}
	 */
	public static ContainerFactory<MerchantInventory> MERCHANT_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#SMOKER}
	 */
	public static ContainerFactory<SmokerInventory> SMOKER_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#STONECUTTER}
	 */
	public static ContainerFactory<StonecutterInventory> STONECUTTER_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#WORKBENCH}
	 */
	public static ContainerFactory<WorkbenchInventory> WORKBENCH_INV_FACTORY;
	
	/**
	 * Creates inventories of the type {@link InventoryType#LOOM}
	 */
	public static ContainerFactory<LoomInventory> LOOM_INV_FACTORY;
	
	public I create(InventoryType type, InventoryHolder holder) {
		return create(type, null, holder);
	}
	
	public I create(ChatComponent title, InventoryHolder holder) {
		return create(null, title, holder);
	}
	
	public abstract I create(InventoryType type, ChatComponent title, InventoryHolder holder);

}
