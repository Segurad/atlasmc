package de.atlasmc;

import java.util.ArrayList;
import java.util.List;

import de.atlasmc.NamespacedKey.Namespaced;
import de.atlasmc.block.data.BlockData;
import de.atlasmc.block.tile.TileEntity;
import de.atlasmc.factory.MetaDataFactory;
import de.atlasmc.factory.TileEntityFactory;
import de.atlasmc.inventory.meta.ItemMeta;

public class Material implements Namespaced {
	
	private static final List<Material> REGISTRI;
	private static short iid;
	public static MetaDataFactory DEFAULT_MDF = null;
	
	public static Material
	AIR,
	VOID_AIR,
	CAVE_AIR,
	BUBBLE_COLUMN,
	STONE,
	GRANITE,
	POLISHED_GRANITE,
	DIORITE,
	POLISHED_DIORITE,
	ANDESITE,
	POLISHED_ANDESITE,
	GRASS_BLOCK,
	DIRT,
	COARSE_DIRT,
	PODZOL,
	CRIMSON_NYLIUM,
	WARPED_NYLIUM,
	COBBLESTONE,
	OAK_PLANKS,
	SPRUCE_PLANKS,
	BIRCH_PLANKS,
	JUNGLE_PLANKS,
	ACACIA_PLANKS,
	DARK_OAK_PLANKS,
	CRIMSON_PLANKS,
	WARPED_PLANKS,
	OAK_SAPLING,
	SPRUCE_SAPLING,
	BIRCH_SAPLING,
	JUNGLE_SAPLING,
	ACACIA_SAPLING,
	DARK_OAK_SAPLING,
	BEDROCK,
	WATER,
	LAVA,
	SAND,
	RED_SAND,
	GRAVEL,
	GOLD_ORE,
	IRON_ORE,
	COAL_ORE,
	NETHER_GOLD_ORE,
	OAK_LOG,
	SPRUCE_LOG,
	BIRCH_LOG,
	JUNGLE_LOG,
	ACACIA_LOG,
	DARK_OAK_LOG,
	CRIMSON_STEM,
	WARPED_STEM,
	STRIPPED_OAK_LOG,
	STRIPPED_SPRUCE_LOG,
	STRIPPED_BIRCH_LOG,
	STRIPPED_JUNGLE_LOG,
	STRIPPED_ACACIA_LOG,
	STRIPPED_DARK_OAK_LOG,
	STRIPPED_CRIMSON_STEM,
	STRIPPED_WARPED_STEM,
	STRIPPED_OAK_WOOD,
	STRIPPED_SPRUCE_WOOD,
	STRIPPED_BIRCH_WOOD,
	STRIPPED_JUNGLE_WOOD,
	STRIPPED_ACACIA_WOOD,
	STRIPPED_DARK_OAK_WOOD,
	STRIPPED_CRIMSON_HYPHAE,
	STRIPPED_WARPED_HYPHAE,
	OAK_WOOD,
	SPRUCE_WOOD,
	BIRCH_WOOD,
	JUNGLE_WOOD,
	ACACIA_WOOD,
	DARK_OAK_WOOD,
	CRIMSON_HYPHAE,
	WARPED_HYPHAE,
	OAK_LEAVES,
	SPRUCE_LEAVES,
	BIRCH_LEAVES,
	JUNGLE_LEAVES,
	ACACIA_LEAVES,
	DARK_OAK_LEAVES,
	SPONGE,
	WET_SPONGE,
	GLASS,
	LAPIS_ORE,
	LAPIS_BLOCK,
	DISPENSER,
	SANDSTONE,
	CHISELED_SANDSTONE,
	CUT_SANDSTONE,
	NOTE_BLOCK,
	POWERED_RAIL,
	DETECTOR_RAIL,
	STICKY_PISTON,
	COBWEB,
	GRASS,
	FERN,
	DEAD_BUSH,
	SEAGRASS,
	TALL_SEAGRASS,
	SEA_PICKLE,
	PISTON,
	PISTON_HEAD,
	MOVING_PISTON,
	WHITE_WOOL,
	ORANGE_WOOL,
	MAGENTA_WOOL,
	LIGHT_BLUE_WOOL,
	YELLOW_WOOL,
	LIME_WOOL,
	PINK_WOOL,
	GRAY_WOOL,
	LIGHT_GRAY_WOOL,
	CYAN_WOOL,
	PURPLE_WOOL,
	BLUE_WOOL,
	BROWN_WOOL,
	GREEN_WOOL,
	RED_WOOL,
	BLACK_WOOL,
	DANDELION,
	POPPY,
	BLUE_ORCHID,
	ALLIUM,
	AZURE_BLUET,
	RED_TULIP,
	ORANGE_TULIP,
	WHITE_TULIP,
	PINK_TULIP,
	OXEYE_DAISY,
	CORNFLOWER,
	LILY_OF_THE_VALLEY,
	WITHER_ROSE,
	BROWN_MUSHROOM,
	RED_MUSHROOM,
	CRIMSON_FUNGUS,
	WARPED_FUNGUS,
	CRIMSON_ROOTS,
	WARPED_ROOTS,
	NETHER_SPROUTS,
	WEEPING_VINES,
	WEEPING_VINES_PLANT,
	TWISTING_VINES,
	TWISTING_VINES_PLANT,
	SUGAR_CANE,
	KELP,
	KELP_PLANT,
	BAMBOO,
	BAMBOO_SAPLING,
	GOLD_BLOCK,
	IRON_BLOCK,
	OAK_SLAB,
	SPRUCE_SLAB,
	BIRCH_SLAB,
	JUNGLE_SLAB,
	ACACIA_SLAB,
	DARK_OAK_SLAB,
	CRIMSON_SLAB,
	WARPED_SLAB,
	STONE_SLAB,
	SMOOTH_STONE_SLAB,
	SANDSTONE_SLAB,
	CUT_SANDSTONE_SLAB,
	PETRIFIED_OAK_SLAB,
	COBBLESTONE_SLAB,
	BRICK_SLAB,
	STONE_BRICK_SLAB,
	NETHER_BRICK_SLAB,
	QUARTZ_SLAB,
	RED_SANDSTONE_SLAB,
	CUT_RED_SANDSTONE_SLAB,
	PURPUR_SLAB,
	PRISMARINE_SLAB,
	PRISMARINE_BRICK_SLAB,
	DARK_PRISMARINE_SLAB,
	SMOOTH_QUARTZ,
	SMOOTH_RED_SANDSTONE,
	SMOOTH_SANDSTONE,
	SMOOTH_STONE,
	BRICKS,
	TNT,
	BOOKSHELF,
	MOSSY_COBBLESTONE,
	OBSIDIAN,
	TORCH,
	WALL_TORCH,
	FIRE,
	SOUL_FIRE,
	END_ROD,
	CHORUS_PLANT,
	CHORUS_FLOWER,
	PURPUR_BLOCK,
	PURPUR_PILLAR,
	PURPUR_STAIRS,
	SPAWNER,
	OAK_STAIRS,
	CHEST,
	REDSTONE_WIRE,
	DIAMOND_ORE,
	DIAMOND_BLOCK,
	CRAFTING_TABLE,
	FARMLAND,
	FURNACE,
	LADDER,
	RAIL,
	COBBLESTONE_STAIRS,
	LEVER,
	STONE_PRESSURE_PLATE,
	OAK_PRESSURE_PLATE,
	SPRUCE_PRESSURE_PLATE,
	BIRCH_PRESSURE_PLATE,
	JUNGLE_PRESSURE_PLATE,
	ACACIA_PRESSURE_PLATE,
	DARK_OAK_PRESSURE_PLATE,
	CRIMSON_PRESSURE_PLATE,
	WARPED_PRESSURE_PLATE,
	POLISHED_BLACKSTONE_PRESSURE_PLATE,
	REDSTONE_ORE,
	REDSTONE_TORCH,
	REDSTONE_WALL_TORCH,
	SNOW,
	ICE,
	FROSTED_ICE,
	SNOW_BLOCK,
	CACTUS,
	CLAY,
	JUKEBOX,
	OAK_FENCE,
	SPRUCE_FENCE,
	BIRCH_FENCE,
	JUNGLE_FENCE,
	ACACIA_FENCE,
	DARK_OAK_FENCE,
	CRIMSON_FENCE,
	WARPED_FENCE,
	PUMPKIN,
	CARVED_PUMPKIN,
	NETHERRACK,
	SOUL_SAND,
	SOUL_SOIL,
	BASALT,
	POLISHED_BASALT,
	SOUL_TORCH,
	SOUL_WALL_TORCH,
	GLOWSTONE,
	NETHER_PORTAL,
	JACK_O_LANTERN,
	OAK_TRAPDOOR,
	SPRUCE_TRAPDOOR,
	BIRCH_TRAPDOOR,
	JUNGLE_TRAPDOOR,
	ACACIA_TRAPDOOR,
	DARK_OAK_TRAPDOOR,
	CRIMSON_TRAPDOOR,
	WARPED_TRAPDOOR,
	INFESTED_STONE,
	INFESTED_COBBLESTONE,
	INFESTED_STONE_BRICKS,
	INFESTED_MOSSY_STONE_BRICKS,
	INFESTED_CRACKED_STONE_BRICKS,
	INFESTED_CHISELED_STONE_BRICKS,
	STONE_BRICKS,
	MOSSY_STONE_BRICKS,
	CRACKED_STONE_BRICKS,
	CHISELED_STONE_BRICKS,
	BROWN_MUSHROOM_BLOCK,
	RED_MUSHROOM_BLOCK,
	MUSHROOM_STEM,
	IRON_BARS,
	CHAIN,
	GLASS_PANE,
	MELON,
	ATTACHED_MELON_STEM,
	ATTACHED_PUMPKIN_STEM,
	MELON_STEM,
	PUMPKIN_STEM,
	VINE,
	OAK_FENCE_GATE,
	SPRUCE_FENCE_GATE,
	BIRCH_FENCE_GATE,
	JUNGLE_FENCE_GATE,
	ACACIA_FENCE_GATE,
	DARK_OAK_FENCE_GATE,
	CRIMSON_FENCE_GATE,
	WARPED_FENCE_GATE,
	BRICK_STAIRS,
	STONE_BRICK_STAIRS,
	MYCELIUM,
	LILY_PAD,
	NETHER_BRICKS,
	CRACKED_NETHER_BRICKS,
	CHISELED_NETHER_BRICKS,
	NETHER_BRICK_FENCE,
	NETHER_BRICK_STAIRS,
	ENCHANTING_TABLE,
	END_PORTAL_FRAME,
	END_PORTAL,
	END_GATEWAY,
	END_STONE,
	END_STONE_BRICKS,
	DRAGON_EGG,
	REDSTONE_LAMP,
	COCOA,
	SANDSTONE_STAIRS,
	EMERALD_ORE,
	ENDER_CHEST,
	TRIPWIRE_HOOK,
	TRIPWIRE,
	EMERALD_BLOCK,
	SPRUCE_STAIRS,
	BIRCH_STAIRS,
	JUNGLE_STAIRS,
	CRIMSON_STAIRS,
	WARPED_STAIRS,
	COMMAND_BLOCK,
	BEACON,
	COBBLESTONE_WALL,
	MOSSY_COBBLESTONE_WALL,
	BRICK_WALL,
	PRISMARINE_WALL,
	RED_SANDSTONE_WALL,
	MOSSY_STONE_BRICK_WALL,
	GRANITE_WALL,
	STONE_BRICK_WALL,
	NETHER_BRICK_WALL,
	ANDESITE_WALL,
	RED_NETHER_BRICK_WALL,
	SANDSTONE_WALL,
	END_STONE_BRICK_WALL,
	DIORITE_WALL,
	BLACKSTONE_WALL,
	POLISHED_BLACKSTONE_WALL,
	POLISHED_BLACKSTONE_BRICK_WALL,
	STONE_BUTTON,
	OAK_BUTTON,
	SPRUCE_BUTTON,
	BIRCH_BUTTON,
	JUNGLE_BUTTON,
	ACACIA_BUTTON,
	DARK_OAK_BUTTON,
	CRIMSON_BUTTON,
	WARPED_BUTTON,
	POLISHED_BLACKSTONE_BUTTON,
	ANVIL,
	CHIPPED_ANVIL,
	DAMAGED_ANVIL,
	TRAPPED_CHEST,
	LIGHT_WEIGHTED_PRESSURE_PLATE,
	HEAVY_WEIGHTED_PRESSURE_PLATE,
	DAYLIGHT_DETECTOR,
	REDSTONE_BLOCK,
	NETHER_QUARTZ_ORE,
	HOPPER,
	CHISELED_QUARTZ_BLOCK,
	QUARTZ_BLOCK,
	QUARTZ_BRICKS,
	QUARTZ_PILLAR,
	QUARTZ_STAIRS,
	ACTIVATOR_RAIL,
	DROPPER,
	WHITE_TERRACOTTA,
	ORANGE_TERRACOTTA,
	MAGENTA_TERRACOTTA,
	LIGHT_BLUE_TERRACOTTA,
	YELLOW_TERRACOTTA,
	LIME_TERRACOTTA,
	PINK_TERRACOTTA,
	GRAY_TERRACOTTA,
	LIGHT_GRAY_TERRACOTTA,
	CYAN_TERRACOTTA,
	PURPLE_TERRACOTTA,
	BLUE_TERRACOTTA,
	BROWN_TERRACOTTA,
	GREEN_TERRACOTTA,
	RED_TERRACOTTA,
	BLACK_TERRACOTTA,
	BARRIER,
	IRON_TRAPDOOR,
	HAY_BLOCK,
	WHITE_CARPET,
	ORANGE_CARPET,
	MAGENTA_CARPET,
	LIGHT_BLUE_CARPET,
	YELLOW_CARPET,
	LIME_CARPET,
	PINK_CARPET,
	GRAY_CARPET,
	LIGHT_GRAY_CARPET,
	CYAN_CARPET,
	PURPLE_CARPET,
	BLUE_CARPET,
	BROWN_CARPET,
	GREEN_CARPET,
	RED_CARPET,
	BLACK_CARPET,
	TERRACOTTA,
	COAL_BLOCK,
	PACKED_ICE,
	ACACIA_STAIRS,
	DARK_OAK_STAIRS,
	SLIME_BLOCK,
	GRASS_PATH,
	SUNFLOWER,
	LILAC,
	ROSE_BUSH,
	PEONY,
	TALL_GRASS,
	LARGE_FERN,
	WHITE_STAINED_GLASS,
	ORANGE_STAINED_GLASS,
	MAGENTA_STAINED_GLASS,
	LIGHT_BLUE_STAINED_GLASS,
	YELLOW_STAINED_GLASS,
	LIME_STAINED_GLASS,
	PINK_STAINED_GLASS,
	GRAY_STAINED_GLASS,
	LIGHT_GRAY_STAINED_GLASS,
	CYAN_STAINED_GLASS,
	PURPLE_STAINED_GLASS,
	BLUE_STAINED_GLASS,
	BROWN_STAINED_GLASS,
	GREEN_STAINED_GLASS,
	RED_STAINED_GLASS,
	BLACK_STAINED_GLASS,
	WHITE_STAINED_GLASS_PANE,
	ORANGE_STAINED_GLASS_PANE,
	MAGENTA_STAINED_GLASS_PANE,
	LIGHT_BLUE_STAINED_GLASS_PANE,
	YELLOW_STAINED_GLASS_PANE,
	LIME_STAINED_GLASS_PANE,
	PINK_STAINED_GLASS_PANE,
	GRAY_STAINED_GLASS_PANE,
	LIGHT_GRAY_STAINED_GLASS_PANE,
	CYAN_STAINED_GLASS_PANE,
	PURPLE_STAINED_GLASS_PANE,
	BLUE_STAINED_GLASS_PANE,
	BROWN_STAINED_GLASS_PANE,
	GREEN_STAINED_GLASS_PANE,
	RED_STAINED_GLASS_PANE,
	BLACK_STAINED_GLASS_PANE,
	PRISMARINE,
	PRISMARINE_BRICKS,
	DARK_PRISMARINE,
	PRISMARINE_STAIRS,
	PRISMARINE_BRICK_STAIRS,
	DARK_PRISMARINE_STAIRS,
	SEA_LANTERN,
	RED_SANDSTONE,
	CHISELLED_RED_SANDSTONE,
	CUT_RED_SANDSTONE,
	RED_SANDSTONE_STAIRS,
	REPEATING_COMMAND_BLOCK,
	CHAIN_COMMAND_BLOCK,
	MAGMA_BLOCK,
	NETHER_WART_BLOCK,
	WARPED_WART_BLOCK,
	RED_NETHER_BRICKS,
	BONE_BLOCK,
	STRUCTURE_VOID,
	OBSERVER,
	SHULKER_BOX,
	WHITE_SHULKER_BOX,
	ORANGE_SHULKER_BOX,
	MAGENTA_SHULKER_BOX,
	LIGHT_BLUE_SHULKER_BOX,
	YELLOW_SHULKER_BOX,
	LIME_SHULKER_BOX,
	PINK_SHULKER_BOX,
	GRAY_SHULKER_BOX,
	LIGHT_GRAY_SHULKER_BOX,
	CYAN_SHULKER_BOX,
	PURPLE_SHULKER_BOX,
	BLUE_SHULKER_BOX,
	BROWN_SHULKER_BOX,
	GREEN_SHULKER_BOX,
	RED_SHULKER_BOX,
	BLACK_SHULKER_BOX,
	WHITE_GLAZED_TERRACOTTA,
	ORANGE_GLAZED_TERRACOTTA,
	MAGENTA_GLAZED_TERRACOTTA,
	LIGHT_BLUE_GLAZED_TERRACOTTA,
	YELLOW_GLAZED_TERRACOTTA,
	LIME_GLAZED_TERRACOTTA,
	PINK_GLAZED_TERRACOTTA,
	GRAY_GLAZED_TERRACOTTA,
	LIGHT_GRAY_GLAZED_TERRACOTTA,
	CYAN_GLAZED_TERRACOTTA,
	PURPLE_GLAZED_TERRACOTTA,
	BLUE_GLAZED_TERRACOTTA,
	BROWN_GLAZED_TERRACOTTA,
	GREEN_GLAZED_TERRACOTTA,
	RED_GLAZED_TERRACOTTA,
	BLACK_GLAZED_TERRACOTTA,
	WHITE_CONCRETE,
	ORANGE_CONCRETE,
	MAGENTA_CONCRETE,
	LIGHT_BLUE_CONCRETE,
	YELLOW_CONCRETE,
	LIME_CONCRETE,
	PINK_CONCRETE,
	GRAY_CONCRETE,
	LIGHT_GRAY_CONCRETE,
	CYAN_CONCRETE,
	PURPLE_CONCRETE,
	BLUE_CONCRETE,
	BROWN_CONCRETE,
	GREEN_CONCRETE,
	RED_CONCRETE,
	BLACK_CONCRETE,
	WHITE_CONCRETE_POWDER,
	ORANGE_CONCRETE_POWDER,
	MAGENTA_CONCRETE_POWDER,
	LIGHT_BLUE_CONCRETE_POWDER,
	YELLOW_CONCRETE_POWDER,
	LIME_CONCRETE_POWDER,
	PINK_CONCRETE_POWDER,
	GRAY_CONCRETE_POWDER,
	LIGHT_GRAY_CONCRETE_POWDER,
	CYAN_CONCRETE_POWDER,
	PURPLE_CONCRETE_POWDER,
	BLUE_CONCRETE_POWDER,
	BROWN_CONCRETE_POWDER,
	GREEN_CONCRETE_POWDER,
	RED_CONCRETE_POWDER,
	BLACK_CONCRETE_POWDER,
	TURTLE_EGG,
	DEAD_TUBE_CORAL_BLOCK,
	DEAD_BRAIN_CORAL_BLOCK,
	DEAD_BUBBLE_CORAL_BLOCK,
	DEAD_FIRE_CORAL_BLOCK,
	DEAD_HORN_CORAL_BLOCK,
	TUBE_CORAL_BLOCK,
	BRAIN_CORAL_BLOCK,
	BUBBLE_CORAL_BLOCK,
	FIRE_CORAL_BLOCK,
	HORN_CORAL_BLOCK,
	TUBE_CORAL,
	BRAIN_CORAL,
	BUBBLE_CORAL,
	FIRE_CORAL,
	HORN_CORAL,
	DEAD_BRAIN_CORAL,
	DEAD_BUBBLE_CORAL,
	DEAD_FIRE_CORAL,
	DEAD_HORN_CORAL,
	DEAD_TUBE_CORAL,
	TUBE_CORAL_FAN,
	BRAIN_CORAL_FAN,
	BUBBLE_CORAL_FAN,
	FIRE_CORAL_FAN,
	HORN_CORAL_FAN,
	DEAD_TUBE_CORAL_FAN,
	DEAD_BRAIN_CORAL_FAN,
	DEAD_BUBBLE_CORAL_FAN,
	DEAD_FIRE_CORAL_FAN,
	DEAD_HORN_CORAL_FAN,
	TUBE_CORAL_WALL_FAN,
	BRAIN_CORAL_WALL_FAN,
	BUBBLE_CORAL_WALL_FAN,
	FIRE_CORAL_WALL_FAN,
	HORN_CORAL_WALL_FAN,
	DEAD_TUBE_CORAL_WALL_FAN,
	DEAD_BRAIN_CORAL_WALL_FAN,
	DEAD_BUBBLE_CORAL_WALL_FAN,
	DEAD_FIRE_CORAL_WALL_FAN,
	DEAD_HORN_CORAL_WALL_FAN,
	BLUE_ICE,
	CONDUIT,
	POLISHED_GRANITE_STAIRS,
	SMOOTH_RED_SANDSTONE_STAIRS,
	MOSSY_STONE_BRICK_STAIRS,
	POLISHED_DIORITE_STAIRS,
	MOSSY_COBBLESTONE_STAIRS,
	END_STONE_BRICK_STAIRS,
	STONE_STAIRS,
	SMOOTH_SANDSTONE_STAIRS,
	SMOOTH_QUARTZ_STAIRS,
	GRANITE_STAIRS,
	ANDESITE_STAIRS,
	RED_NETHER_BRICK_STAIRS,
	POLISHED_ANDESITE_STAIRS,
	DIORITE_STAIRS,
	POLISHED_GRANITE_SLAB,
	SMOOTH_RED_SANDSTONE_SLAB,
	MOSSY_STONE_BRICK_SLAB,
	POLISHED_DIORITE_SLAB,
	MOSSY_COBBLESTONE_SLAB,
	END_STONE_BRICK_SLAB,
	SMOOTH_SANDSTONE_SLAB,
	SMOOTH_QUARTZ_SLAB,
	GRANITE_SLAB,
	ANDESITE_SLAB,
	RED_NETHER_BRICK_SLAB,
	POLISHED_ANDESITE_SLAB,
	DIORITE_SLAB,
	SCAFFOLDING,
	IRON_DOOR,
	OAK_DOOR,
	SPRUCE_DOOR,
	BIRCH_DOOR,
	JUNGLE_DOOR,
	ACACIA_DOOR,
	DARK_OAK_DOOR,
	CRIMSON_DOOR,
	WARPED_DOOR,
	REPEATER,
	COMPARATOR,
	STRUCTURE_BLOCK,
	JIGSAW,
	TURTLE_HELMET,
	SCUTE,
	FLINT_AND_STEEL,
	APPLE,
	ARROW,
	COAL,
	CHARCOAL,
	DIAMOND,
	IRON_INGOT,
	GOLD_INGOT,
	NETHERITE_INGOT,
	NETHERITE_SCRAP,
	WOODEN_SWORD,
	WOODEN_SHOVEL,
	WOODEN_PICKAXE,
	WOODEN_AXE,
	WOODEN_HOE,
	STONE_SWORD,
	STONE_SHOVEL,
	STONE_PICKAXE,
	STONE_AXE,
	STONE_HOE,
	GOLDEN_SWORD,
	GOLDEN_SHOVEL,
	GOLDEN_PICKAXE,
	GOLDEN_AXE,
	GOLDEN_HOE,
	IRON_SWORD,
	IRON_SHOVEL,
	IRON_PICKAXE,
	IRON_AXE,
	IRON_HOE,
	DIAMOND_SWORD,
	DIAMOND_SHOVEL,
	DIAMOND_PICKAXE,
	DIAMOND_AXE,
	DIAMOND_HOE,
	NETHERITE_SWORD,
	NETHERITE_SHOVEL,
	NETHERITE_PICKAXE,
	NETHERITE_AXE,
	NETHERITE_HOE,
	STICK,
	BOWL,
	MUSHROOM_STEW,
	STRING,
	FEATHER,
	GUN_POWDER,
	WHEAT_SEEDS,
	WHEAT,
	BREAD,
	LEATHER_HELMET,
	LEATHER_CHESTPLATE,
	LEATHER_LEGGINGS,
	LEATHER_BOOTS,
	CHAINMAIL_HELMET,
	CHAINMAIL_CHESTPLATE,
	CHAINMAIL_LEGGINGS,
	CHAINMAIL_BOOTS,
	IRON_HELMET,
	IRON_CHESTPLATE,
	IRON_LEGGINGS,
	IRON_BOOTS,
	DIAMOND_HELMET,
	DIAMOND_CHESTPLATE,
	DIAMOND_LEGGINGS,
	DIAMOND_BOOTS,
	GOLDEN_HELMET,
	GOLDEN_CHESTPLATE,
	GOLDEN_LEGGINGS,
	GOLDEN_BOOTS,
	NETHERITE_HELMET,
	NETHERITE_CHESTPLATE,
	NETHERITE_LEGGINGS,
	NETHERITE_BOOTS,
	FLINT,
	PORKCHOP,
	COOKED_PORKCHOP,
	PAINTING,
	GOLDEN_APPLE,
	ENCHANTED_GOLDEN_APPLE,
	OAK_SIGN,
	SPRUCE_SIGN,
	BIRCH_SIGN,
	JUNGLE_SIGN,
	ACACIA_SIGN,
	DARK_OAK_SIGN,
	CRIMSON_SIGN,
	WARPED_SIGN,
	OAK_WALL_SIGN,
	SPRUCE_WALL_SIGN,
	BIRCH_WALL_SIGN,
	JUNGLE_WALL_SIGN,
	ACACIA_WALL_SIGN,
	DARK_OAK_WALL_SIGN,
	CRIMSON_WALL_SIGN,
	WARPED_WALL_SIGN,
	BUCKET,
	WATER_BUCKET,
	LAVA_BUCKET,
	MINECART,
	SADDLE,
	REDSTONE,
	SNOWBALL,
	OAK_BOAT,
	LEATHER,
	MILK_BUCKET,
	PUFFERFISH_BUCKET,
	SALMON_BUCKET,
	COD_BUCKET,
	TROPICAL_FISH_BUCKET,
	BRICK,
	CLAY_BALL,
	DRIED_KELP_BLOCK,
	PAPER,
	BOOK,
	SLIME_BALL,
	CHEST_MINECART,
	FURNACE_MINECART,
	EGG,
	COMPASS,
	FISHING_ROD,
	CLOCK,
	GLOWSTONE_DUST,
	COD,
	SALMON,
	TROPICAL_FISH,
	PUFFERFISH,
	COOKED_COD,
	COOKED_SALMON,
	INK_SAC,
	COCOA_BEANS,
	LAPIS_LAZULI,
	WHITE_DYE,
	ORANGE_DYE,
	MAGENTA_DYE,
	LIGHT_BLUE_DYE,
	YELLOW_DYE,
	LIME_DYE,
	PINK_DYE,
	GRAY_DYE,
	LIGHT_GRAY_DYE,
	CYAN_DYE,
	PURPLE_DYE,
	BLUE_DYE,
	BROWN_DYE,
	GREEN_DYE,
	RED_DYE,
	BLACK_DYE,
	BONE_MEAL,
	BONE,
	SUGAR,
	CAKE,
	WHITE_BED,
	ORANGE_BED,
	MAGENTA_BED,
	LIGHT_BLUE_BED,
	YELLOW_BED,
	LIME_BED,
	PINK_BED,
	GRAY_BED,
	LIGHT_GRAY_BED,
	CYAN_BED,
	PURPLE_BED,
	BLUE_BED,
	BROWN_BED,
	GREEN_BED,
	RED_BED,
	BLACK_BED,
	COOKIE,
	FILLED_MAP,
	SHEARS,
	MELON_SLICE,
	DRIED_KELP,
	PUMPKIN_SEED,
	MELON_SEEDS,
	BEEF,
	COOKED_BEEF,
	CHICKEN,
	COOKED_CHICKEN,
	ROTTEN_FLESH,
	ENDER_PEARL,
	BLAZE_ROD,
	GHAST_TEAR,
	GOLD_NUGGET,
	NETHER_WART,
	POTION,
	GLASS_BOTTLE,
	SPIDER_EYE,
	FERMENTED_SPIDER_EYE,
	BLAZE_POWDER,
	MAGMA_CREAM,
	BREWING_STAND,
	CAULDRON,
	ENDER_EYE,
	GLISTERING_MELON_SLICE,
	BAT_SPAWN_EGG,
	BEE_SPAWN_EGG,
	BLAZE_SPAWN_EGG,
	CAT_SPAWN_EGG,
	CAVE_SPIDER_SPAWN_EGG,
	CHICKEN_SPAWN_EGG,
	COD_SPAWN_EGG,
	COW_SPAWN_EGG,
	CREEPER_SPAWN_EGG,
	DOLPHIN_SPAWN_EGG,
	DONKEY_SPAWN_EGG,
	DROWNED_SPAWN_EGG,
	ELDER_GUARDIAN_SPAWN_EGG,
	ENDERMAN_SPAWN_EGG,
	ENDERMITE_SPAWN_EGG,
	EVOKER_SPAWN_EGG,
	FOX_SPAWN_EGG,
	GHAST_SPAWN_EGG,
	GUARDIAN_SPAWN_EGG,
	HOGLIN_SPAWN_EGG,
	HORSE_SPAWN_EGG,
	HUSK_SPAWN_EGG,
	LLAMA_SPAWN_EGG,
	MAGMA_CUBE_SPAWN_EGG,
	MOOSHROOM_SPAWN_EGG,
	MULE_SPAWN_EGG,
	OCELOT_SPAWN_EGG,
	PANDA_SPAWN_EGG,
	PARROT_SPAWN_EGG,
	PHANTOM_SPAWN_EGG,
	PIG_SPAWN_EGG,
	PIGLIN_SPAWN_EGG,
	PIGLIN_BRUTE_SPAWN_EGG,
	PILLAGER_SPAWN_EGG,
	POLAR_BEAR_SPAWN_EGG,
	PUFFERFISH_SPAWN_EGG,
	RABBIT_SPAWN_EGG,
	RAVAGER_SPAWN_EGG,
	SALMON_SPAWN_EGG,
	SHEEP_SPAWN_EGG,
	SHULKER_SPAWN_EGG,
	SILVERFISH_SPAWN_EGG,
	SKELETON_SPAWN_EGG,
	SKELETON_HORSE_SPAWN_EGG,
	SLIME_SPAWN_EGG,
	SPIDER_SPAWN_EGG,
	SQUID_SPAWN_EGG,
	STRAY_SPAWN_EGG,
	STRIDER_SPAWN_EGG,
	TRADER_LLAMA_SPAWN_EGG,
	TROPICAL_FISH_SPAWN_EGG,
	TURTLE_SPAWN_EGG,
	VEX_SPAWN_EGG,
	VILLAGER_SPAWN_EGG,
	VINDICATOR_SPAWN_EGG,
	WANDERING_TRADER_SPAWN_EGG,
	WITCH_SPAWN_EGG,
	WITHER_SKELETON_SPAWN_EGG,
	WOLF_SPAWN_EGG,
	ZOGLIN_SPAWN_EGG,
	ZOMBIE_SPAWN_EGG,
	ZOMBIE_VILLAGER_SPAWN_EGG,
	ZOMBIEFIED_PIG_SPAWN_EGG,
	EXPERIENCE_BOTTLE,
	FIRE_CHARGE,
	WRITEABLE_BOOK,
	WRITTEN_BOOK,
	EMERALD,
	ITEM_FRAME,
	FLOWER_POT,
	POTTED_OAK_SAPLING,
	POTTED_SPRUCE_SAPLING,
	POTTED_BIRCH_SAPLING,
	POTTED_JUNGLE_SAPLING,
	POTTED_ACACIA_SAPLING,
	POTTED_DARK_OAK_SAPLING,
	POTTED_FERN,
	POTTED_DANDELION,
	POTTED_POPPY,
	POTTED_BLUE_ORCHID,
	POTTED_ALLIUM,
	POTTED_AZURE_BLUET,
	POTTED_RED_TULIP,
	POTTED_ORANGE_TULIP,
	POTTED_WHITE_TULIP,
	POTTED_PINK_TULIP,
	POTTED_OXEYE_DAISY,
	POTTED_CORNFLOWER,
	POTTED_LILY_OF_THE_VALLEY,
	POTTED_WITHER_ROSE,
	POTTED_RED_MUSHROOM,
	POTTED_BROWN_MUSHROOM,
	POTTED_DEAD_BUSH,
	POTTED_CACTUS,
	POTTED_CRIMSON_FUNGUS,
	POTTED_WARPED_FUNGUS,
	POTTED_CRIMSON_ROOTS,
	POTTED_WARPED_ROOTS,
	CARROT,
	CARROTS,
	POTATO,
	POTATOES,
	BAKED_POTATO,
	POISONOUS_POTATO,
	MAP,
	GOLDEN_CARROT,
	SKELETON_SKULL,
	SKELETON_WALL_SKULL,
	WITHER_SKELETON_SKULL,
	WITHER_SKELETON_WALL_SKULL,
	PLAYER_HEAD,
	PLAYER_WALL_HEAD,
	ZOMBIE_HEAD,
	ZOMBIE_WALL_HEAD,
	CREEPER_HEAD,
	CREEPER_WALL_HEAD,
	DRAGON_HEAD,
	DRAGON_WALL_HEAD,
	CARROT_ON_A_STICK,
	WARPED_FUNGUS_ON_A_STICK,
	NETHER_STAR,
	PUMPKIN_PIE,
	FIREWORK_ROCKET,
	FIREWORK_STAR,
	ENCHANTED_BOOK,
	NETHER_BRICK,
	QUARTZ,
	TNT_MINECART,
	HOPPER_MINECART,
	PRISMARINE_SHARD,
	PRISMARINE_CRYSTALS,
	RABBIT,
	COOKED_RABBIT,
	RABBIT_STEW,
	RABBIT_FOOT,
	RABBIT_HIDE,
	ARMOR_STAND,
	IRON_HORSE_ARMOR,
	GOLDEN_HORSE_ARMOR,
	DIAMOND_HORSE_ARMOR,
	LEATHER_HORSE_ARMOR,
	LEAD,
	NAME_TAG,
	COMMAND_BLOCK_MINECART,
	MUTTON,
	COOKED_MUTTON,
	WHITE_BANNER,
	ORANGE_BANNER,
	MAGENTA_BANNER,
	LIGHT_BLUE_BANNER,
	YELLOW_BANNER,
	LIME_BANNER,
	PINK_BANNER,
	GRAY_BANNER,
	LIGHT_GRAY_BANNER,
	CYAN_BANNER,
	PURPLE_BANNER,
	BLUE_BANNER,
	BROWN_BANNER,
	GREEN_BANNER,
	RED_BANNER,
	BLACK_BANNER,
	WHITE_WALL_BANNER,
	ORANGE_WALL_BANNER,
	MAGENTA_WALL_BANNER,
	LIGHT_BLUE_WALL_BANNER,
	YELLOW_WALL_BANNER,
	LIME_WALL_BANNER,
	PINK_WALL_BANNER,
	GRAY_WALL_BANNER,
	LIGHT_GRAY_WALL_BANNER,
	CYAN_WALL_BANNER,
	PURPLE_WALL_BANNER,
	BLUE_WALL_BANNER,
	BROWN_WALL_BANNER,
	GREEN_WALL_BANNER,
	RED_WALL_BANNER,
	BLACK_WALL_BANNER,
	END_CRYSTAL,
	CHORUS_FRUIT,
	POPPED_CHORUS_FRUIT,
	BEETROOT,
	BEETROOTS,
	BEETROOT_SOUP,
	DRAGON_BREATH,
	SPLASH_POTION,
	SPECTRAL_ARROW,
	TIPPED_ARROW,
	LINGERING_POTION,
	SHIELD,
	ELYTRA,
	SPRUCE_BOAT,
	BIRCH_BOAT,
	JUNGLE_BOAT,
	ACACIA_BOAT,
	DARK_OAK_BOAT,
	TOTEM_OF_UNDYING,
	SHULKER_SHELL,
	IRON_NUGGET,
	KNOWLEDGE_BOOK,
	DEBUG_STICK,
	MUSIC_DISC_13,
	MUSIC_DISC_CAT,
	MUSIC_DISC_BLOCKS,
	MUSIC_DISC_CHIRP,
	MUSIC_DISC_FAR,
	MUSIC_DISC_MALL,
	MUSIC_DISC_STAL,
	MUSIC_DISC_STRAD,
	MUSIC_DISC_WARD,
	MUSIC_DISC_11,
	MUSIC_DISC_WAIT,
	MUSIC_DISC_PIGSTEP,
	TRIDENT,
	PHANTOM_MEMBRANE,
	NAUTILUS_SHELL,
	HEART_OF_THE_SEA,
	CROSSBOW,
	SUSPICIOUS_STEW,
	LOOM,
	FLOWER_BANNER_PATTERN,
	CREEPER_BANNER_PATTERN,
	SKULL_BANNER_PATTERN,
	MOJANG_BANNER_PATTERN,
	GLOBE_BANNER_PATTERN,
	PIGLIN_BANNER_PATTERN,
	COMPOSTER,
	BARREL,
	SMOKER,
	BLAST_FURNACE,
	CARTOGRAPHY_TABLE,
	FLETCHING_TABLE,
	GRINDSTONE,
	LECTERN,
	SMITHING_TABLE,
	STONECUTTER,
	BELL,
	LANTERN,
	SOUL_LANTERN,
	SWEET_BERRIES,
	SWEET_BERRY_BUSH,
	CAMPFIRE,
	SOUL_CAMPFIRE,
	SHROOMLIGHT,
	HONEYCOMB,
	BEE_NEST,
	BEEHIVE,
	HONEY_BOTTLE,
	HONEY_BLOCK,
	HONEYCOMB_BLOCK,
	LODESTONE,
	NETHERITE_BLOCK,
	ANCIENT_DEBRIS,
	TARGET,
	CRYING_OBSIDIAN,
	BLACKSTONE,
	BLACKSTONE_SLAB,
	BLACKSTONE_STAIRS,
	GILDED_BLACKSTONE,
	POLISHED_BLACKSTONE,
	POLISHED_BLACKSTONE_SLAB,
	POLISHED_BLACKSTONE_STAIRS,
	CHISELED_POLISHED_BLACKSTONE,
	POLISHED_BLACKSTONE_BRICKS,
	POLISHED_BLACKSTONE_BRICK_SLAB,
	POLISHED_BLACKSTONE_BRICK_STAIRS,
	CRACKED_POLISHED_BLACKSTONE_BRICKS,
	RESPAWN_ANCHOR;
	
	static {
		REGISTRI = new ArrayList<Material>();
	}
	
	private final String name;
	private final short itemID, bid, namespaceID;
	private final byte max;
	private final float toughness, explosionResistance;
	private MetaDataFactory mdf;
	private TileEntityFactory tef;
	
	/**
	 * 
	 * @param name the Material's name
	 * @param itemID the protocol ItemID or -1 if it has no Item
	 * @param blockID the protocol BlockID or -1 if it has no Block
	 * @param mdf null to use MetaDataFactory.DEFAULT
	 */
	public Material(int namespaceID, String name, short itemID, short blockID, byte maxAmount, float toughness, float explosionResistance, MetaDataFactory mdf) {
		if (name == null) throw new IllegalArgumentException("Name can not be null!");
		if (NamespacedKey.getNamespace(namespaceID) == null) throw new IllegalArgumentException("Unknown namespace!");
		this.name = name;
		this.itemID = itemID;
		this.bid = blockID;
		this.max = maxAmount;
		this.namespaceID = (short) namespaceID;
		this.toughness = toughness;
		this.explosionResistance = explosionResistance;
		registerMaterial();
		setMetaDataFactory(mdf);
	}
	
	public Material(int namespaceID, String name, byte maxAmount, float toughness, float explosionResistance, MetaDataFactory mdf) {
		this(namespaceID, name, true, (short) -1, maxAmount, toughness, explosionResistance, mdf);
	}
	
	public Material(int namespaceID, String name, boolean hasItem, short blockID, byte maxAmount, float toughness, float explosionResistance) {
		this(namespaceID, name, hasItem, blockID, maxAmount, toughness, explosionResistance, null);
	}
	
	public Material(int namespaceID, String name, short blockID, byte maxAmount, float toughness, float explosionResistance, MetaDataFactory mdf) {
		this(namespaceID, name, true, blockID, maxAmount, toughness, explosionResistance, mdf);
	}
	
	public Material(int namespaceID, String name, boolean hasItem, short blockID, byte maxAmount, float toughness, float explosionResistance, MetaDataFactory mdf) {
		if (name == null) throw new IllegalArgumentException("Name can not be null!");
		if (NamespacedKey.getNamespace(namespaceID) == null) throw new IllegalArgumentException("Unknown namespace!");
		this.name = name.toLowerCase();
		this.itemID = hasItem ? iid++ : -1;
		this.bid = blockID;
		this.max = maxAmount;
		this.namespaceID = (short) namespaceID;
		this.toughness = toughness;
		this.explosionResistance = explosionResistance;
		registerMaterial();
		setMetaDataFactory(mdf);
	}
	
	public String getName() {
		return name;
	}
	
	public short getItemID() {
		return itemID;
	}
	
	public short getBlockID() {
		return bid;
	}
	
	public float getToughness() {
		return toughness;
	}
	
	public float getExplosionResistance() {
		return explosionResistance;
	}
	
	/**
	 * Returns the Material's MetaDataFactory or MetaDataFactory#DEFAULT
	 * @return MetaDataFactory
	 */
	public MetaDataFactory getMetaDataFactory() {
		if (mdf == null) return DEFAULT_MDF;
		return mdf;
	}
	
	public void setMetaDataFactory(MetaDataFactory factory) {
		mdf = factory;
	}
	
	public TileEntityFactory getTileEntityFactory() {
		return tef;
	}
	
	public void setTileEntityFactory(TileEntityFactory factory) {
		this.tef = factory;
	}
	
	public ItemMeta createItemMeta() {
		MetaDataFactory mdf = getMetaDataFactory();
		if (mdf == null) return DEFAULT_MDF.createMeta(this);
		return mdf.createMeta(this);
	}
	
	public BlockData createBlockData() {
		MetaDataFactory mdf = getMetaDataFactory();
		if (mdf == null) return DEFAULT_MDF.createData(this);
		return mdf.createData(this);
	}
	
	public TileEntity createTileEntity() {
		TileEntityFactory tf = getTileEntityFactory();
		if (tf != null) return tf.createTile(this);
		return null;
	}
	
	public boolean isValidMeta(ItemMeta meta) {
		MetaDataFactory mdf = getMetaDataFactory();
		if (mdf == null) return DEFAULT_MDF.isValidMeta(meta);
		return mdf.isValidMeta(meta);
	}
	
	public boolean isValidData(BlockData data) {
		if (data.getMaterial() != this) return false;
		MetaDataFactory mdf = getMetaDataFactory();
		if (mdf == null) return DEFAULT_MDF.isValidData(data);
		return mdf.isValidData(data);
	}
	
	public boolean isValidTile(TileEntity tile) {
		if (!isBlock()) return false;
		TileEntityFactory tf = getTileEntityFactory();
		return tf != null ? tf.isValidTile(tile) : false;
	}
	
	public boolean isItem() {
		return itemID != -1;
	}
	
	public boolean isBlock() {
		return bid != -1;
	}
	
	public short getNamespaceID() {
		return namespaceID;
	}
	
	private final void registerMaterial() {
		if (getMaterial(getNamespaceID(), getName()) != null) {
			throw new Error("Material already registered: " + getNamespacedName());
		}
		REGISTRI.add(this);
	}
	
	public final void unregister() {
		REGISTRI.remove(this);
	}
	
	public int getMaxAmount() {
		return max;
	}
	
	public boolean isAir() {
		return this == AIR || this == CAVE_AIR || this == VOID_AIR;
	}

	public static List<Material> getMaterials() {
		return new ArrayList<Material>(REGISTRI);
	}
	
	public static Material getByItemID(int itemID) {
		for (Material mat : REGISTRI) {
			if (mat.getItemID() == itemID) return mat;
		}
		return null;
	}
	
	/**
	 * 
	 * @param name the materials name with or without the namespace
	 * @return first material with the name if no namespace if given
	 */
	public static Material getByName(String name) {
		if (name == null) throw new IllegalArgumentException("Name can not be null!");
		if (name.contains(":")) {
			String[] parts = name.split(":");
			if (parts.length > 2) throw new IllegalArgumentException("Illegal NamespacedKey format: " + name);
			return getMaterial(parts[0], parts[1]);
		}
		for (Material mat : REGISTRI) {
			if (mat.getName().equals(name)) return mat;
		}
		return null;
	}
	
	public static Material getMaterial(String namespace, String name) {
		int namespaceID = NamespacedKey.getNamespaceID(namespace);
		if (namespaceID == -1) throw new IllegalArgumentException("Unknown namespace!");
		return getMaterial(namespaceID, name);
	}
	
	public static Material getMaterial(NamespacedKey key) {
		return getMaterial(key.getNamespaceID(), key.getKey());
	}
	
	public static Material getMaterial(int namespaceID, String name) {
		if (NamespacedKey.getNamespace(namespaceID) == null) throw new IllegalArgumentException("Unknown namespace!");
		for (Material mat : REGISTRI) {
			if (mat.getNamespaceID() == namespaceID && mat.getName().equals(name)) return mat;
		}
		return null;
	}
	
}
