package de.atlasmc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import de.atlasmc.NamespacedKey.Namespaced;
import de.atlasmc.block.data.BlockData;
import de.atlasmc.block.tile.TileEntity;
import de.atlasmc.entity.EntityType;
import de.atlasmc.factory.MetaDataFactory;
import de.atlasmc.factory.TileEntityFactory;
import de.atlasmc.inventory.ItemStack;
import de.atlasmc.inventory.meta.ItemMeta;
import de.atlasmc.registry.Registries;
import de.atlasmc.registry.Registry;
import de.atlasmc.registry.RegistryHolder;
import de.atlasmc.util.annotation.NotNull;
import de.atlasmc.util.annotation.Nullable;

@RegistryHolder(key="atlas:material")
public class Material implements Namespaced {
	
	private static Registry<Material> registry;
	private static final Map<Integer, Material> REGISTRI_BY_IID;
	
	public static Material
	ACACIA_BOAT,
	ACACIA_BUTTON,
	ACACIA_CHEST_BOAT,
	ACACIA_DOOR,
	ACACIA_FENCE,
	ACACIA_FENCE_GATE,
	ACACIA_HANGING_SIGN,
	ACACIA_LEAVES,
	ACACIA_LOG,
	ACACIA_PLANKS,
	ACACIA_PRESSURE_PLATE,
	ACACIA_SAPLING,
	ACACIA_SIGN,
	ACACIA_SLAB,
	ACACIA_STAIRS,
	ACACIA_TRAPDOOR,
	ACACIA_WALL_HANGING_SIGN,
	ACACIA_WALL_SIGN,
	ACACIA_WOOD,
	ACTIVATOR_RAIL,
	AIR,
	ALLAY_SPAWN_EGG,
	ALLIUM,
	AMETHYST_BLOCK,
	AMETHYST_CLUSTER,
	AMETHYST_SHARD,
	ANCIENT_DEBRIS,
	ANDESITE,
	ANDESITE_SLAB,
	ANDESITE_STAIRS,
	ANDESITE_WALL,
	ANGLER_POTTERY_SHERD,
	ANVIL,
	APPLE,
	ARCHER_POTTERY_SHERD,
	ARMOR_STAND,
	ARMS_UP_POTTERY_SHERD,
	ARROW,
	ATTACHED_MELON_STEM,
	ATTACHED_PUMPKIN_STEM,
	AXOLOTL_BUCKET,
	AXOLOTL_SPAWN_EGG,
	AZALEA,
	AZALEA_LEAVES,
	AZURE_BLUET,
	BAKED_POTATO,
	BAMBOO,
	BAMBOO_BLOCK,
	BAMBOO_BUTTON,
	BAMBOO_CHEST_RAFT,
	BAMBOO_DOOR,
	BAMBOO_FENCE,
	BAMBOO_FENCE_GATE,
	BAMBOO_HANGING_SIGN,
	BAMBOO_MOSAIC,
	BAMBOO_MOSAIC_SLAB,
	BAMBOO_MOSAIC_STAIRS,
	BAMBOO_PLANKS,
	BAMBOO_PRESSURE_PLATE,
	BAMBOO_RAFT,
	BAMBOO_SAPLING,
	BAMBOO_SIGN,
	BAMBOO_SLAB,
	BAMBOO_STAIRS,
	BAMBOO_TRAPDOOR,
	BAMBOO_WALL_HANGING_SIGN,
	BAMBOO_WALL_SIGN,
	BARREL,
	BARRIER,
	BASALT,
	BAT_SPAWN_EGG,
	BEACON,
	BEDROCK,
	BEEF,
	BEEHIVE,
	BEETROOT,
	BEETROOTS,
	BEETROOT_SEEDS,
	BEETROOT_SOUP,
	BEE_NEST,
	BEE_SPAWN_EGG,
	BELL,
	BIG_DRIPLEAF,
	BIG_DRIPLEAF_STEM,
	BIRCH_BOAT,
	BIRCH_BUTTON,
	BIRCH_CHEST_BOAT,
	BIRCH_DOOR,
	BIRCH_FENCE,
	BIRCH_FENCE_GATE,
	BIRCH_HANGING_SIGN,
	BIRCH_LEAVES,
	BIRCH_LOG,
	BIRCH_PLANKS,
	BIRCH_PRESSURE_PLATE,
	BIRCH_SAPLING,
	BIRCH_SIGN,
	BIRCH_SLAB,
	BIRCH_STAIRS,
	BIRCH_TRAPDOOR,
	BIRCH_WALL_HANGING_SIGN,
	BIRCH_WALL_SIGN,
	BIRCH_WOOD,
	BLACKSTONE,
	BLACKSTONE_SLAB,
	BLACKSTONE_STAIRS,
	BLACKSTONE_WALL,
	BLACK_BANNER,
	BLACK_BED,
	BLACK_CANDLE,
	BLACK_CANDLE_CAKE,
	BLACK_CARPET,
	BLACK_CONCRETE,
	BLACK_CONCRETE_POWDER,
	BLACK_DYE,
	BLACK_GLAZED_TERRACOTTA,
	BLACK_SHULKER_BOX,
	BLACK_STAINED_GLASS,
	BLACK_STAINED_GLASS_PANE,
	BLACK_TERRACOTTA,
	BLACK_WALL_BANNER,
	BLACK_WOOL,
	BLADE_POTTERY_SHERD,
	BLAST_FURNACE,
	BLAZE_POWDER,
	BLAZE_ROD,
	BLAZE_SPAWN_EGG,
	BLUE_BANNER,
	BLUE_BED,
	BLUE_CANDLE,
	BLUE_CANDLE_CAKE,
	BLUE_CARPET,
	BLUE_CONCRETE,
	BLUE_CONCRETE_POWDER,
	BLUE_DYE,
	BLUE_GLAZED_TERRACOTTA,
	BLUE_ICE,
	BLUE_ORCHID,
	BLUE_SHULKER_BOX,
	BLUE_STAINED_GLASS,
	BLUE_STAINED_GLASS_PANE,
	BLUE_TERRACOTTA,
	BLUE_WALL_BANNER,
	BLUE_WOOL,
	BONE,
	BONE_BLOCK,
	BONE_MEAL,
	BOOK,
	BOOKSHELF,
	BOW,
	BOWL,
	BRAIN_CORAL,
	BRAIN_CORAL_BLOCK,
	BRAIN_CORAL_FAN,
	BRAIN_CORAL_WALL_FAN,
	BREAD,
	BREWER_POTTERY_SHERD,
	BREWING_STAND,
	BRICK,
	BRICKS,
	BRICK_SLAB,
	BRICK_STAIRS,
	BRICK_WALL,
	BROWN_BANNER,
	BROWN_BED,
	BROWN_CANDLE,
	BROWN_CANDLE_CAKE,
	BROWN_CARPET,
	BROWN_CONCRETE,
	BROWN_CONCRETE_POWDER,
	BROWN_DYE,
	BROWN_GLAZED_TERRACOTTA,
	BROWN_MUSHROOM,
	BROWN_MUSHROOM_BLOCK,
	BROWN_SHULKER_BOX,
	BROWN_STAINED_GLASS,
	BROWN_STAINED_GLASS_PANE,
	BROWN_TERRACOTTA,
	BROWN_WALL_BANNER,
	BROWN_WOOL,
	BRUSH,
	BUBBLE_COLUMN,
	BUBBLE_CORAL,
	BUBBLE_CORAL_BLOCK,
	BUBBLE_CORAL_FAN,
	BUBBLE_CORAL_WALL_FAN,
	BUCKET,
	BUDDING_AMETHYST,
	BUNDLE,
	BURN_POTTERY_SHERD,
	CACTUS,
	CAKE,
	CALCITE,
	CALIBRATED_SCULK_SENSOR,
	CAMEL_SPAWN_EGG,
	CAMPFIRE,
	CANDLE,
	CANDLE_CAKE,
	CARROT,
	CARROTS,
	CARROT_ON_A_STICK,
	CARTOGRAPHY_TABLE,
	CARVED_PUMPKIN,
	CAT_SPAWN_EGG,
	CAULDRON,
	CAVE_AIR,
	CAVE_SPIDER_SPAWN_EGG,
	CAVE_VINES,
	CAVE_VINES_PLANT,
	CHAIN,
	CHAINMAIL_BOOTS,
	CHAINMAIL_CHESTPLATE,
	CHAINMAIL_HELMET,
	CHAINMAIL_LEGGINGS,
	CHAIN_COMMAND_BLOCK,
	CHARCOAL,
	CHERRY_BOAT,
	CHERRY_BUTTON,
	CHERRY_CHEST_BOAT,
	CHERRY_DOOR,
	CHERRY_FENCE,
	CHERRY_FENCE_GATE,
	CHERRY_HANGING_SIGN,
	CHERRY_LEAVES,
	CHERRY_LOG,
	CHERRY_PLANKS,
	CHERRY_PRESSURE_PLATE,
	CHERRY_SAPLING,
	CHERRY_SIGN,
	CHERRY_SLAB,
	CHERRY_STAIRS,
	CHERRY_TRAPDOOR,
	CHERRY_WALL_HANGING_SIGN,
	CHERRY_WALL_SIGN,
	CHERRY_WOOD,
	CHEST,
	CHEST_MINECART,
	CHICKEN,
	CHICKEN_SPAWN_EGG,
	CHIPPED_ANVIL,
	CHISELED_BOOKSHELF,
	CHISELED_DEEPSLATE,
	CHISELED_NETHER_BRICKS,
	CHISELED_POLISHED_BLACKSTONE,
	CHISELED_QUARTZ_BLOCK,
	CHISELED_RED_SANDSTONE,
	CHISELED_SANDSTONE,
	CHISELED_STONE_BRICKS,
	CHORUS_FLOWER,
	CHORUS_FRUIT,
	CHORUS_PLANT,
	CLAY,
	CLAY_BALL,
	CLOCK,
	COAL,
	COAL_BLOCK,
	COAL_ORE,
	COARSE_DIRT,
	COAST_ARMOR_TRIM_SMITHING_TEMPLATE,
	COBBLED_DEEPSLATE,
	COBBLED_DEEPSLATE_SLAB,
	COBBLED_DEEPSLATE_STAIRS,
	COBBLED_DEEPSLATE_WALL,
	COBBLESTONE,
	COBBLESTONE_SLAB,
	COBBLESTONE_STAIRS,
	COBBLESTONE_WALL,
	COBWEB,
	COCOA,
	COCOA_BEANS,
	COD,
	COD_BUCKET,
	COD_SPAWN_EGG,
	COMMAND_BLOCK,
	COMMAND_BLOCK_MINECART,
	COMPARATOR,
	COMPASS,
	COMPOSTER,
	CONDUIT,
	COOKED_BEEF,
	COOKED_CHICKEN,
	COOKED_COD,
	COOKED_MUTTON,
	COOKED_PORKCHOP,
	COOKED_RABBIT,
	COOKED_SALMON,
	COOKIE,
	COPPER_BLOCK,
	COPPER_INGOT,
	COPPER_ORE,
	CORNFLOWER,
	COW_SPAWN_EGG,
	CRACKED_DEEPSLATE_BRICKS,
	CRACKED_DEEPSLATE_TILES,
	CRACKED_NETHER_BRICKS,
	CRACKED_POLISHED_BLACKSTONE_BRICKS,
	CRACKED_STONE_BRICKS,
	CRAFTING_TABLE,
	CREEPER_BANNER_PATTERN,
	CREEPER_HEAD,
	CREEPER_SPAWN_EGG,
	CREEPER_WALL_HEAD,
	CRIMSON_BUTTON,
	CRIMSON_DOOR,
	CRIMSON_FENCE,
	CRIMSON_FENCE_GATE,
	CRIMSON_FUNGUS,
	CRIMSON_HANGING_SIGN,
	CRIMSON_HYPHAE,
	CRIMSON_NYLIUM,
	CRIMSON_PLANKS,
	CRIMSON_PRESSURE_PLATE,
	CRIMSON_ROOTS,
	CRIMSON_SIGN,
	CRIMSON_SLAB,
	CRIMSON_STAIRS,
	CRIMSON_STEM,
	CRIMSON_TRAPDOOR,
	CRIMSON_WALL_HANGING_SIGN,
	CRIMSON_WALL_SIGN,
	CROSSBOW,
	CRYING_OBSIDIAN,
	CUT_COPPER,
	CUT_COPPER_SLAB,
	CUT_COPPER_STAIRS,
	CUT_RED_SANDSTONE,
	CUT_RED_SANDSTONE_SLAB,
	CUT_SANDSTONE,
	CUT_SANDSTONE_SLAB,
	CYAN_BANNER,
	CYAN_BED,
	CYAN_CANDLE,
	CYAN_CANDLE_CAKE,
	CYAN_CARPET,
	CYAN_CONCRETE,
	CYAN_CONCRETE_POWDER,
	CYAN_DYE,
	CYAN_GLAZED_TERRACOTTA,
	CYAN_SHULKER_BOX,
	CYAN_STAINED_GLASS,
	CYAN_STAINED_GLASS_PANE,
	CYAN_TERRACOTTA,
	CYAN_WALL_BANNER,
	CYAN_WOOL,
	DAMAGED_ANVIL,
	DANDELION,
	DANGER_POTTERY_SHERD,
	DARK_OAK_BOAT,
	DARK_OAK_BUTTON,
	DARK_OAK_CHEST_BOAT,
	DARK_OAK_DOOR,
	DARK_OAK_FENCE,
	DARK_OAK_FENCE_GATE,
	DARK_OAK_HANGING_SIGN,
	DARK_OAK_LEAVES,
	DARK_OAK_LOG,
	DARK_OAK_PLANKS,
	DARK_OAK_PRESSURE_PLATE,
	DARK_OAK_SAPLING,
	DARK_OAK_SIGN,
	DARK_OAK_SLAB,
	DARK_OAK_STAIRS,
	DARK_OAK_TRAPDOOR,
	DARK_OAK_WALL_HANGING_SIGN,
	DARK_OAK_WALL_SIGN,
	DARK_OAK_WOOD,
	DARK_PRISMARINE,
	DARK_PRISMARINE_SLAB,
	DARK_PRISMARINE_STAIRS,
	DAYLIGHT_DETECTOR,
	DEAD_BRAIN_CORAL,
	DEAD_BRAIN_CORAL_BLOCK,
	DEAD_BRAIN_CORAL_FAN,
	DEAD_BRAIN_CORAL_WALL_FAN,
	DEAD_BUBBLE_CORAL,
	DEAD_BUBBLE_CORAL_BLOCK,
	DEAD_BUBBLE_CORAL_FAN,
	DEAD_BUBBLE_CORAL_WALL_FAN,
	DEAD_BUSH,
	DEAD_FIRE_CORAL,
	DEAD_FIRE_CORAL_BLOCK,
	DEAD_FIRE_CORAL_FAN,
	DEAD_FIRE_CORAL_WALL_FAN,
	DEAD_HORN_CORAL,
	DEAD_HORN_CORAL_BLOCK,
	DEAD_HORN_CORAL_FAN,
	DEAD_HORN_CORAL_WALL_FAN,
	DEAD_TUBE_CORAL,
	DEAD_TUBE_CORAL_BLOCK,
	DEAD_TUBE_CORAL_FAN,
	DEAD_TUBE_CORAL_WALL_FAN,
	DEBUG_STICK,
	DECORATED_POT,
	DEEPSLATE,
	DEEPSLATE_BRICKS,
	DEEPSLATE_BRICK_SLAB,
	DEEPSLATE_BRICK_STAIRS,
	DEEPSLATE_BRICK_WALL,
	DEEPSLATE_COAL_ORE,
	DEEPSLATE_COPPER_ORE,
	DEEPSLATE_DIAMOND_ORE,
	DEEPSLATE_EMERALD_ORE,
	DEEPSLATE_GOLD_ORE,
	DEEPSLATE_IRON_ORE,
	DEEPSLATE_LAPIS_ORE,
	DEEPSLATE_REDSTONE_ORE,
	DEEPSLATE_TILES,
	DEEPSLATE_TILE_SLAB,
	DEEPSLATE_TILE_STAIRS,
	DEEPSLATE_TILE_WALL,
	DETECTOR_RAIL,
	DIAMOND,
	DIAMOND_AXE,
	DIAMOND_BLOCK,
	DIAMOND_BOOTS,
	DIAMOND_CHESTPLATE,
	DIAMOND_HELMET,
	DIAMOND_HOE,
	DIAMOND_HORSE_ARMOR,
	DIAMOND_LEGGINGS,
	DIAMOND_ORE,
	DIAMOND_PICKAXE,
	DIAMOND_SHOVEL,
	DIAMOND_SWORD,
	DIORITE,
	DIORITE_SLAB,
	DIORITE_STAIRS,
	DIORITE_WALL,
	DIRT,
	DIRT_PATH,
	DISC_FRAGMENT_5,
	DISPENSER,
	DOLPHIN_SPAWN_EGG,
	DONKEY_SPAWN_EGG,
	DRAGON_BREATH,
	DRAGON_EGG,
	DRAGON_HEAD,
	DRAGON_WALL_HEAD,
	DRIED_KELP,
	DRIED_KELP_BLOCK,
	DRIPSTONE_BLOCK,
	DROPPER,
	DROWNED_SPAWN_EGG,
	DUNE_ARMOR_TRIM_SMITHING_TEMPLATE,
	ECHO_SHARD,
	EGG,
	ELDER_GUARDIAN_SPAWN_EGG,
	ELYTRA,
	EMERALD,
	EMERALD_BLOCK,
	EMERALD_ORE,
	ENCHANTED_BOOK,
	ENCHANTED_GOLDEN_APPLE,
	ENCHANTING_TABLE,
	ENDERMAN_SPAWN_EGG,
	ENDERMITE_SPAWN_EGG,
	ENDER_CHEST,
	ENDER_DRAGON_SPAWN_EGG,
	ENDER_EYE,
	ENDER_PEARL,
	END_CRYSTAL,
	END_GATEWAY,
	END_PORTAL,
	END_PORTAL_FRAME,
	END_ROD,
	END_STONE,
	END_STONE_BRICKS,
	END_STONE_BRICK_SLAB,
	END_STONE_BRICK_STAIRS,
	END_STONE_BRICK_WALL,
	EVOKER_SPAWN_EGG,
	EXPERIENCE_BOTTLE,
	EXPLORER_POTTERY_SHERD,
	EXPOSED_COPPER,
	EXPOSED_CUT_COPPER,
	EXPOSED_CUT_COPPER_SLAB,
	EXPOSED_CUT_COPPER_STAIRS,
	EYE_ARMOR_TRIM_SMITHING_TEMPLATE,
	FARMLAND,
	FEATHER,
	FERMENTED_SPIDER_EYE,
	FERN,
	FILLED_MAP,
	FIRE,
	FIREWORK_ROCKET,
	FIREWORK_STAR,
	FIRE_CHARGE,
	FIRE_CORAL,
	FIRE_CORAL_BLOCK,
	FIRE_CORAL_FAN,
	FIRE_CORAL_WALL_FAN,
	FISHING_ROD,
	FLETCHING_TABLE,
	FLINT,
	FLINT_AND_STEEL,
	FLOWERING_AZALEA,
	FLOWERING_AZALEA_LEAVES,
	FLOWER_BANNER_PATTERN,
	FLOWER_POT,
	FOX_SPAWN_EGG,
	FRIEND_POTTERY_SHERD,
	FROGSPAWN,
	FROG_SPAWN_EGG,
	FROSTED_ICE,
	FURNACE,
	FURNACE_MINECART,
	GHAST_SPAWN_EGG,
	GHAST_TEAR,
	GILDED_BLACKSTONE,
	GLASS,
	GLASS_BOTTLE,
	GLASS_PANE,
	GLISTERING_MELON_SLICE,
	GLOBE_BANNER_PATTERN,
	GLOWSTONE,
	GLOWSTONE_DUST,
	GLOW_BERRIES,
	GLOW_INK_SAC,
	GLOW_ITEM_FRAME,
	GLOW_LICHEN,
	GLOW_SQUID_SPAWN_EGG,
	GOAT_HORN,
	GOAT_SPAWN_EGG,
	GOLDEN_APPLE,
	GOLDEN_AXE,
	GOLDEN_BOOTS,
	GOLDEN_CARROT,
	GOLDEN_CHESTPLATE,
	GOLDEN_HELMET,
	GOLDEN_HOE,
	GOLDEN_HORSE_ARMOR,
	GOLDEN_LEGGINGS,
	GOLDEN_PICKAXE,
	GOLDEN_SHOVEL,
	GOLDEN_SWORD,
	GOLD_BLOCK,
	GOLD_INGOT,
	GOLD_NUGGET,
	GOLD_ORE,
	GRANITE,
	GRANITE_SLAB,
	GRANITE_STAIRS,
	GRANITE_WALL,
	GRASS,
	GRASS_BLOCK,
	GRAVEL,
	GRAY_BANNER,
	GRAY_BED,
	GRAY_CANDLE,
	GRAY_CANDLE_CAKE,
	GRAY_CARPET,
	GRAY_CONCRETE,
	GRAY_CONCRETE_POWDER,
	GRAY_DYE,
	GRAY_GLAZED_TERRACOTTA,
	GRAY_SHULKER_BOX,
	GRAY_STAINED_GLASS,
	GRAY_STAINED_GLASS_PANE,
	GRAY_TERRACOTTA,
	GRAY_WALL_BANNER,
	GRAY_WOOL,
	GREEN_BANNER,
	GREEN_BED,
	GREEN_CANDLE,
	GREEN_CANDLE_CAKE,
	GREEN_CARPET,
	GREEN_CONCRETE,
	GREEN_CONCRETE_POWDER,
	GREEN_DYE,
	GREEN_GLAZED_TERRACOTTA,
	GREEN_SHULKER_BOX,
	GREEN_STAINED_GLASS,
	GREEN_STAINED_GLASS_PANE,
	GREEN_TERRACOTTA,
	GREEN_WALL_BANNER,
	GREEN_WOOL,
	GRINDSTONE,
	GUARDIAN_SPAWN_EGG,
	GUNPOWDER,
	HANGING_ROOTS,
	HAY_BLOCK,
	HEARTBREAK_POTTERY_SHERD,
	HEART_OF_THE_SEA,
	HEART_POTTERY_SHERD,
	HEAVY_WEIGHTED_PRESSURE_PLATE,
	HOGLIN_SPAWN_EGG,
	HONEYCOMB,
	HONEYCOMB_BLOCK,
	HONEY_BLOCK,
	HONEY_BOTTLE,
	HOPPER,
	HOPPER_MINECART,
	HORN_CORAL,
	HORN_CORAL_BLOCK,
	HORN_CORAL_FAN,
	HORN_CORAL_WALL_FAN,
	HORSE_SPAWN_EGG,
	HOST_ARMOR_TRIM_SMITHING_TEMPLATE,
	HOWL_POTTERY_SHERD,
	HUSK_SPAWN_EGG,
	ICE,
	INFESTED_CHISELED_STONE_BRICKS,
	INFESTED_COBBLESTONE,
	INFESTED_CRACKED_STONE_BRICKS,
	INFESTED_DEEPSLATE,
	INFESTED_MOSSY_STONE_BRICKS,
	INFESTED_STONE,
	INFESTED_STONE_BRICKS,
	INK_SAC,
	IRON_AXE,
	IRON_BARS,
	IRON_BLOCK,
	IRON_BOOTS,
	IRON_CHESTPLATE,
	IRON_DOOR,
	IRON_GOLEM_SPAWN_EGG,
	IRON_HELMET,
	IRON_HOE,
	IRON_HORSE_ARMOR,
	IRON_INGOT,
	IRON_LEGGINGS,
	IRON_NUGGET,
	IRON_ORE,
	IRON_PICKAXE,
	IRON_SHOVEL,
	IRON_SWORD,
	IRON_TRAPDOOR,
	ITEM_FRAME,
	JACK_O_LANTERN,
	JIGSAW,
	JUKEBOX,
	JUNGLE_BOAT,
	JUNGLE_BUTTON,
	JUNGLE_CHEST_BOAT,
	JUNGLE_DOOR,
	JUNGLE_FENCE,
	JUNGLE_FENCE_GATE,
	JUNGLE_HANGING_SIGN,
	JUNGLE_LEAVES,
	JUNGLE_LOG,
	JUNGLE_PLANKS,
	JUNGLE_PRESSURE_PLATE,
	JUNGLE_SAPLING,
	JUNGLE_SIGN,
	JUNGLE_SLAB,
	JUNGLE_STAIRS,
	JUNGLE_TRAPDOOR,
	JUNGLE_WALL_HANGING_SIGN,
	JUNGLE_WALL_SIGN,
	JUNGLE_WOOD,
	KELP,
	KELP_PLANT,
	KNOWLEDGE_BOOK,
	LADDER,
	LANTERN,
	LAPIS_BLOCK,
	LAPIS_LAZULI,
	LAPIS_ORE,
	LARGE_AMETHYST_BUD,
	LARGE_FERN,
	LAVA,
	LAVA_BUCKET,
	LAVA_CAULDRON,
	LEAD,
	LEATHER,
	LEATHER_BOOTS,
	LEATHER_CHESTPLATE,
	LEATHER_HELMET,
	LEATHER_HORSE_ARMOR,
	LEATHER_LEGGINGS,
	LECTERN,
	LEVER,
	LIGHT,
	LIGHTNING_ROD,
	LIGHT_BLUE_BANNER,
	LIGHT_BLUE_BED,
	LIGHT_BLUE_CANDLE,
	LIGHT_BLUE_CANDLE_CAKE,
	LIGHT_BLUE_CARPET,
	LIGHT_BLUE_CONCRETE,
	LIGHT_BLUE_CONCRETE_POWDER,
	LIGHT_BLUE_DYE,
	LIGHT_BLUE_GLAZED_TERRACOTTA,
	LIGHT_BLUE_SHULKER_BOX,
	LIGHT_BLUE_STAINED_GLASS,
	LIGHT_BLUE_STAINED_GLASS_PANE,
	LIGHT_BLUE_TERRACOTTA,
	LIGHT_BLUE_WALL_BANNER,
	LIGHT_BLUE_WOOL,
	LIGHT_GRAY_BANNER,
	LIGHT_GRAY_BED,
	LIGHT_GRAY_CANDLE,
	LIGHT_GRAY_CANDLE_CAKE,
	LIGHT_GRAY_CARPET,
	LIGHT_GRAY_CONCRETE,
	LIGHT_GRAY_CONCRETE_POWDER,
	LIGHT_GRAY_DYE,
	LIGHT_GRAY_GLAZED_TERRACOTTA,
	LIGHT_GRAY_SHULKER_BOX,
	LIGHT_GRAY_STAINED_GLASS,
	LIGHT_GRAY_STAINED_GLASS_PANE,
	LIGHT_GRAY_TERRACOTTA,
	LIGHT_GRAY_WALL_BANNER,
	LIGHT_GRAY_WOOL,
	LIGHT_WEIGHTED_PRESSURE_PLATE,
	LILAC,
	LILY_OF_THE_VALLEY,
	LILY_PAD,
	LIME_BANNER,
	LIME_BED,
	LIME_CANDLE,
	LIME_CANDLE_CAKE,
	LIME_CARPET,
	LIME_CONCRETE,
	LIME_CONCRETE_POWDER,
	LIME_DYE,
	LIME_GLAZED_TERRACOTTA,
	LIME_SHULKER_BOX,
	LIME_STAINED_GLASS,
	LIME_STAINED_GLASS_PANE,
	LIME_TERRACOTTA,
	LIME_WALL_BANNER,
	LIME_WOOL,
	LINGERING_POTION,
	LLAMA_SPAWN_EGG,
	LODESTONE,
	LOOM,
	MAGENTA_BANNER,
	MAGENTA_BED,
	MAGENTA_CANDLE,
	MAGENTA_CANDLE_CAKE,
	MAGENTA_CARPET,
	MAGENTA_CONCRETE,
	MAGENTA_CONCRETE_POWDER,
	MAGENTA_DYE,
	MAGENTA_GLAZED_TERRACOTTA,
	MAGENTA_SHULKER_BOX,
	MAGENTA_STAINED_GLASS,
	MAGENTA_STAINED_GLASS_PANE,
	MAGENTA_TERRACOTTA,
	MAGENTA_WALL_BANNER,
	MAGENTA_WOOL,
	MAGMA_BLOCK,
	MAGMA_CREAM,
	MAGMA_CUBE_SPAWN_EGG,
	MANGROVE_BOAT,
	MANGROVE_BUTTON,
	MANGROVE_CHEST_BOAT,
	MANGROVE_DOOR,
	MANGROVE_FENCE,
	MANGROVE_FENCE_GATE,
	MANGROVE_HANGING_SIGN,
	MANGROVE_LEAVES,
	MANGROVE_LOG,
	MANGROVE_PLANKS,
	MANGROVE_PRESSURE_PLATE,
	MANGROVE_PROPAGULE,
	MANGROVE_ROOTS,
	MANGROVE_SIGN,
	MANGROVE_SLAB,
	MANGROVE_STAIRS,
	MANGROVE_TRAPDOOR,
	MANGROVE_WALL_HANGING_SIGN,
	MANGROVE_WALL_SIGN,
	MANGROVE_WOOD,
	MAP,
	MEDIUM_AMETHYST_BUD,
	MELON,
	MELON_SEEDS,
	MELON_SLICE,
	MELON_STEM,
	MILK_BUCKET,
	MINECART,
	MINER_POTTERY_SHERD,
	MOJANG_BANNER_PATTERN,
	MOOSHROOM_SPAWN_EGG,
	MOSSY_COBBLESTONE,
	MOSSY_COBBLESTONE_SLAB,
	MOSSY_COBBLESTONE_STAIRS,
	MOSSY_COBBLESTONE_WALL,
	MOSSY_STONE_BRICKS,
	MOSSY_STONE_BRICK_SLAB,
	MOSSY_STONE_BRICK_STAIRS,
	MOSSY_STONE_BRICK_WALL,
	MOSS_BLOCK,
	MOSS_CARPET,
	MOURNER_POTTERY_SHERD,
	MOVING_PISTON,
	MUD,
	MUDDY_MANGROVE_ROOTS,
	MUD_BRICKS,
	MUD_BRICK_SLAB,
	MUD_BRICK_STAIRS,
	MUD_BRICK_WALL,
	MULE_SPAWN_EGG,
	MUSHROOM_STEM,
	MUSHROOM_STEW,
	MUSIC_DISC_11,
	MUSIC_DISC_13,
	MUSIC_DISC_5,
	MUSIC_DISC_BLOCKS,
	MUSIC_DISC_CAT,
	MUSIC_DISC_CHIRP,
	MUSIC_DISC_FAR,
	MUSIC_DISC_MALL,
	MUSIC_DISC_MELLOHI,
	MUSIC_DISC_OTHERSIDE,
	MUSIC_DISC_PIGSTEP,
	MUSIC_DISC_RELIC,
	MUSIC_DISC_STAL,
	MUSIC_DISC_STRAD,
	MUSIC_DISC_WAIT,
	MUSIC_DISC_WARD,
	MUTTON,
	MYCELIUM,
	NAME_TAG,
	NAUTILUS_SHELL,
	NETHERITE_AXE,
	NETHERITE_BLOCK,
	NETHERITE_BOOTS,
	NETHERITE_CHESTPLATE,
	NETHERITE_HELMET,
	NETHERITE_HOE,
	NETHERITE_INGOT,
	NETHERITE_LEGGINGS,
	NETHERITE_PICKAXE,
	NETHERITE_SCRAP,
	NETHERITE_SHOVEL,
	NETHERITE_SWORD,
	NETHERITE_UPGRADE_SMITHING_TEMPLATE,
	NETHERRACK,
	NETHER_BRICK,
	NETHER_BRICKS,
	NETHER_BRICK_FENCE,
	NETHER_BRICK_SLAB,
	NETHER_BRICK_STAIRS,
	NETHER_BRICK_WALL,
	NETHER_GOLD_ORE,
	NETHER_PORTAL,
	NETHER_QUARTZ_ORE,
	NETHER_SPROUTS,
	NETHER_STAR,
	NETHER_WART,
	NETHER_WART_BLOCK,
	NOTE_BLOCK,
	OAK_BOAT,
	OAK_BUTTON,
	OAK_CHEST_BOAT,
	OAK_DOOR,
	OAK_FENCE,
	OAK_FENCE_GATE,
	OAK_HANGING_SIGN,
	OAK_LEAVES,
	OAK_LOG,
	OAK_PLANKS,
	OAK_PRESSURE_PLATE,
	OAK_SAPLING,
	OAK_SIGN,
	OAK_SLAB,
	OAK_STAIRS,
	OAK_TRAPDOOR,
	OAK_WALL_HANGING_SIGN,
	OAK_WALL_SIGN,
	OAK_WOOD,
	OBSERVER,
	OBSIDIAN,
	OCELOT_SPAWN_EGG,
	OCHRE_FROGLIGHT,
	ORANGE_BANNER,
	ORANGE_BED,
	ORANGE_CANDLE,
	ORANGE_CANDLE_CAKE,
	ORANGE_CARPET,
	ORANGE_CONCRETE,
	ORANGE_CONCRETE_POWDER,
	ORANGE_DYE,
	ORANGE_GLAZED_TERRACOTTA,
	ORANGE_SHULKER_BOX,
	ORANGE_STAINED_GLASS,
	ORANGE_STAINED_GLASS_PANE,
	ORANGE_TERRACOTTA,
	ORANGE_TULIP,
	ORANGE_WALL_BANNER,
	ORANGE_WOOL,
	OXEYE_DAISY,
	OXIDIZED_COPPER,
	OXIDIZED_CUT_COPPER,
	OXIDIZED_CUT_COPPER_SLAB,
	OXIDIZED_CUT_COPPER_STAIRS,
	PACKED_ICE,
	PACKED_MUD,
	PAINTING,
	PANDA_SPAWN_EGG,
	PAPER,
	PARROT_SPAWN_EGG,
	PEARLESCENT_FROGLIGHT,
	PEONY,
	PETRIFIED_OAK_SLAB,
	PHANTOM_MEMBRANE,
	PHANTOM_SPAWN_EGG,
	PIGLIN_BANNER_PATTERN,
	PIGLIN_BRUTE_SPAWN_EGG,
	PIGLIN_HEAD,
	PIGLIN_SPAWN_EGG,
	PIGLIN_WALL_HEAD,
	PIG_SPAWN_EGG,
	PILLAGER_SPAWN_EGG,
	PINK_BANNER,
	PINK_BED,
	PINK_CANDLE,
	PINK_CANDLE_CAKE,
	PINK_CARPET,
	PINK_CONCRETE,
	PINK_CONCRETE_POWDER,
	PINK_DYE,
	PINK_GLAZED_TERRACOTTA,
	PINK_PETALS,
	PINK_SHULKER_BOX,
	PINK_STAINED_GLASS,
	PINK_STAINED_GLASS_PANE,
	PINK_TERRACOTTA,
	PINK_TULIP,
	PINK_WALL_BANNER,
	PINK_WOOL,
	PISTON,
	PISTON_HEAD,
	PITCHER_CROP,
	PITCHER_PLANT,
	PITCHER_POD,
	PLAYER_HEAD,
	PLAYER_WALL_HEAD,
	PLENTY_POTTERY_SHERD,
	PODZOL,
	POINTED_DRIPSTONE,
	POISONOUS_POTATO,
	POLAR_BEAR_SPAWN_EGG,
	POLISHED_ANDESITE,
	POLISHED_ANDESITE_SLAB,
	POLISHED_ANDESITE_STAIRS,
	POLISHED_BASALT,
	POLISHED_BLACKSTONE,
	POLISHED_BLACKSTONE_BRICKS,
	POLISHED_BLACKSTONE_BRICK_SLAB,
	POLISHED_BLACKSTONE_BRICK_STAIRS,
	POLISHED_BLACKSTONE_BRICK_WALL,
	POLISHED_BLACKSTONE_BUTTON,
	POLISHED_BLACKSTONE_PRESSURE_PLATE,
	POLISHED_BLACKSTONE_SLAB,
	POLISHED_BLACKSTONE_STAIRS,
	POLISHED_BLACKSTONE_WALL,
	POLISHED_DEEPSLATE,
	POLISHED_DEEPSLATE_SLAB,
	POLISHED_DEEPSLATE_STAIRS,
	POLISHED_DEEPSLATE_WALL,
	POLISHED_DIORITE,
	POLISHED_DIORITE_SLAB,
	POLISHED_DIORITE_STAIRS,
	POLISHED_GRANITE,
	POLISHED_GRANITE_SLAB,
	POLISHED_GRANITE_STAIRS,
	POPPED_CHORUS_FRUIT,
	POPPY,
	PORKCHOP,
	POTATO,
	POTATOES,
	POTION,
	POTTED_ACACIA_SAPLING,
	POTTED_ALLIUM,
	POTTED_AZALEA_BUSH,
	POTTED_AZURE_BLUET,
	POTTED_BAMBOO,
	POTTED_BIRCH_SAPLING,
	POTTED_BLUE_ORCHID,
	POTTED_BROWN_MUSHROOM,
	POTTED_CACTUS,
	POTTED_CHERRY_SAPLING,
	POTTED_CORNFLOWER,
	POTTED_CRIMSON_FUNGUS,
	POTTED_CRIMSON_ROOTS,
	POTTED_DANDELION,
	POTTED_DARK_OAK_SAPLING,
	POTTED_DEAD_BUSH,
	POTTED_FERN,
	POTTED_FLOWERING_AZALEA_BUSH,
	POTTED_JUNGLE_SAPLING,
	POTTED_LILY_OF_THE_VALLEY,
	POTTED_MANGROVE_PROPAGULE,
	POTTED_OAK_SAPLING,
	POTTED_ORANGE_TULIP,
	POTTED_OXEYE_DAISY,
	POTTED_PINK_TULIP,
	POTTED_POPPY,
	POTTED_RED_MUSHROOM,
	POTTED_RED_TULIP,
	POTTED_SPRUCE_SAPLING,
	POTTED_TORCHFLOWER,
	POTTED_WARPED_FUNGUS,
	POTTED_WARPED_ROOTS,
	POTTED_WHITE_TULIP,
	POTTED_WITHER_ROSE,
	POWDER_SNOW,
	POWDER_SNOW_BUCKET,
	POWDER_SNOW_CAULDRON,
	POWERED_RAIL,
	PRISMARINE,
	PRISMARINE_BRICKS,
	PRISMARINE_BRICK_SLAB,
	PRISMARINE_BRICK_STAIRS,
	PRISMARINE_CRYSTALS,
	PRISMARINE_SHARD,
	PRISMARINE_SLAB,
	PRISMARINE_STAIRS,
	PRISMARINE_WALL,
	PRIZE_POTTERY_SHERD,
	PUFFERFISH,
	PUFFERFISH_BUCKET,
	PUFFERFISH_SPAWN_EGG,
	PUMPKIN,
	PUMPKIN_PIE,
	PUMPKIN_SEEDS,
	PUMPKIN_STEM,
	PURPLE_BANNER,
	PURPLE_BED,
	PURPLE_CANDLE,
	PURPLE_CANDLE_CAKE,
	PURPLE_CARPET,
	PURPLE_CONCRETE,
	PURPLE_CONCRETE_POWDER,
	PURPLE_DYE,
	PURPLE_GLAZED_TERRACOTTA,
	PURPLE_SHULKER_BOX,
	PURPLE_STAINED_GLASS,
	PURPLE_STAINED_GLASS_PANE,
	PURPLE_TERRACOTTA,
	PURPLE_WALL_BANNER,
	PURPLE_WOOL,
	PURPUR_BLOCK,
	PURPUR_PILLAR,
	PURPUR_SLAB,
	PURPUR_STAIRS,
	QUARTZ,
	QUARTZ_BLOCK,
	QUARTZ_BRICKS,
	QUARTZ_PILLAR,
	QUARTZ_SLAB,
	QUARTZ_STAIRS,
	RABBIT,
	RABBIT_FOOT,
	RABBIT_HIDE,
	RABBIT_SPAWN_EGG,
	RABBIT_STEW,
	RAIL,
	RAISER_ARMOR_TRIM_SMITHING_TEMPLATE,
	RAVAGER_SPAWN_EGG,
	RAW_COPPER,
	RAW_COPPER_BLOCK,
	RAW_GOLD,
	RAW_GOLD_BLOCK,
	RAW_IRON,
	RAW_IRON_BLOCK,
	RECOVERY_COMPASS,
	REDSTONE,
	REDSTONE_BLOCK,
	REDSTONE_LAMP,
	REDSTONE_ORE,
	REDSTONE_TORCH,
	REDSTONE_WALL_TORCH,
	REDSTONE_WIRE,
	RED_BANNER,
	RED_BED,
	RED_CANDLE,
	RED_CANDLE_CAKE,
	RED_CARPET,
	RED_CONCRETE,
	RED_CONCRETE_POWDER,
	RED_DYE,
	RED_GLAZED_TERRACOTTA,
	RED_MUSHROOM,
	RED_MUSHROOM_BLOCK,
	RED_NETHER_BRICKS,
	RED_NETHER_BRICK_SLAB,
	RED_NETHER_BRICK_STAIRS,
	RED_NETHER_BRICK_WALL,
	RED_SAND,
	RED_SANDSTONE,
	RED_SANDSTONE_SLAB,
	RED_SANDSTONE_STAIRS,
	RED_SANDSTONE_WALL,
	RED_SHULKER_BOX,
	RED_STAINED_GLASS,
	RED_STAINED_GLASS_PANE,
	RED_TERRACOTTA,
	RED_TULIP,
	RED_WALL_BANNER,
	RED_WOOL,
	REINFORCED_DEEPSLATE,
	REPEATER,
	REPEATING_COMMAND_BLOCK,
	RESPAWN_ANCHOR,
	RIB_ARMOR_TRIM_SMITHING_TEMPLATE,
	ROOTED_DIRT,
	ROSE_BUSH,
	ROTTEN_FLESH,
	SADDLE,
	SALMON,
	SALMON_BUCKET,
	SALMON_SPAWN_EGG,
	SAND,
	SANDSTONE,
	SANDSTONE_SLAB,
	SANDSTONE_STAIRS,
	SANDSTONE_WALL,
	SCAFFOLDING,
	SCULK,
	SCULK_CATALYST,
	SCULK_SENSOR,
	SCULK_SHRIEKER,
	SCULK_VEIN,
	SCUTE,
	SEAGRASS,
	SEA_LANTERN,
	SEA_PICKLE,
	SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE,
	SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE,
	SHEAF_POTTERY_SHERD,
	SHEARS,
	SHEEP_SPAWN_EGG,
	SHELTER_POTTERY_SHERD,
	SHIELD,
	SHROOMLIGHT,
	SHULKER_BOX,
	SHULKER_SHELL,
	SHULKER_SPAWN_EGG,
	SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE,
	SILVERFISH_SPAWN_EGG,
	SKELETON_HORSE_SPAWN_EGG,
	SKELETON_SKULL,
	SKELETON_SPAWN_EGG,
	SKELETON_WALL_SKULL,
	SKULL_BANNER_PATTERN,
	SKULL_POTTERY_SHERD,
	SLIME_BALL,
	SLIME_BLOCK,
	SLIME_SPAWN_EGG,
	SMALL_AMETHYST_BUD,
	SMALL_DRIPLEAF,
	SMITHING_TABLE,
	SMOKER,
	SMOOTH_BASALT,
	SMOOTH_QUARTZ,
	SMOOTH_QUARTZ_SLAB,
	SMOOTH_QUARTZ_STAIRS,
	SMOOTH_RED_SANDSTONE,
	SMOOTH_RED_SANDSTONE_SLAB,
	SMOOTH_RED_SANDSTONE_STAIRS,
	SMOOTH_SANDSTONE,
	SMOOTH_SANDSTONE_SLAB,
	SMOOTH_SANDSTONE_STAIRS,
	SMOOTH_STONE,
	SMOOTH_STONE_SLAB,
	SNIFFER_EGG,
	SNIFFER_SPAWN_EGG,
	SNORT_POTTERY_SHERD,
	SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE,
	SNOW,
	SNOWBALL,
	SNOW_BLOCK,
	SNOW_GOLEM_SPAWN_EGG,
	SOUL_CAMPFIRE,
	SOUL_FIRE,
	SOUL_LANTERN,
	SOUL_SAND,
	SOUL_SOIL,
	SOUL_TORCH,
	SOUL_WALL_TORCH,
	SPAWNER,
	SPECTRAL_ARROW,
	SPIDER_EYE,
	SPIDER_SPAWN_EGG,
	SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE,
	SPLASH_POTION,
	SPONGE,
	SPORE_BLOSSOM,
	SPRUCE_BOAT,
	SPRUCE_BUTTON,
	SPRUCE_CHEST_BOAT,
	SPRUCE_DOOR,
	SPRUCE_FENCE,
	SPRUCE_FENCE_GATE,
	SPRUCE_HANGING_SIGN,
	SPRUCE_LEAVES,
	SPRUCE_LOG,
	SPRUCE_PLANKS,
	SPRUCE_PRESSURE_PLATE,
	SPRUCE_SAPLING,
	SPRUCE_SIGN,
	SPRUCE_SLAB,
	SPRUCE_STAIRS,
	SPRUCE_TRAPDOOR,
	SPRUCE_WALL_HANGING_SIGN,
	SPRUCE_WALL_SIGN,
	SPRUCE_WOOD,
	SPYGLASS,
	SQUID_SPAWN_EGG,
	STICK,
	STICKY_PISTON,
	STONE,
	STONECUTTER,
	STONE_AXE,
	STONE_BRICKS,
	STONE_BRICK_SLAB,
	STONE_BRICK_STAIRS,
	STONE_BRICK_WALL,
	STONE_BUTTON,
	STONE_HOE,
	STONE_PICKAXE,
	STONE_PRESSURE_PLATE,
	STONE_SHOVEL,
	STONE_SLAB,
	STONE_STAIRS,
	STONE_SWORD,
	STRAY_SPAWN_EGG,
	STRIDER_SPAWN_EGG,
	STRING,
	STRIPPED_ACACIA_LOG,
	STRIPPED_ACACIA_WOOD,
	STRIPPED_BAMBOO_BLOCK,
	STRIPPED_BIRCH_LOG,
	STRIPPED_BIRCH_WOOD,
	STRIPPED_CHERRY_LOG,
	STRIPPED_CHERRY_WOOD,
	STRIPPED_CRIMSON_HYPHAE,
	STRIPPED_CRIMSON_STEM,
	STRIPPED_DARK_OAK_LOG,
	STRIPPED_DARK_OAK_WOOD,
	STRIPPED_JUNGLE_LOG,
	STRIPPED_JUNGLE_WOOD,
	STRIPPED_MANGROVE_LOG,
	STRIPPED_MANGROVE_WOOD,
	STRIPPED_OAK_LOG,
	STRIPPED_OAK_WOOD,
	STRIPPED_SPRUCE_LOG,
	STRIPPED_SPRUCE_WOOD,
	STRIPPED_WARPED_HYPHAE,
	STRIPPED_WARPED_STEM,
	STRUCTURE_BLOCK,
	STRUCTURE_VOID,
	SUGAR,
	SUGAR_CANE,
	SUNFLOWER,
	SUSPICIOUS_GRAVEL,
	SUSPICIOUS_SAND,
	SUSPICIOUS_STEW,
	SWEET_BERRIES,
	SWEET_BERRY_BUSH,
	TADPOLE_BUCKET,
	TADPOLE_SPAWN_EGG,
	TALL_GRASS,
	TALL_SEAGRASS,
	TARGET,
	TERRACOTTA,
	TIDE_ARMOR_TRIM_SMITHING_TEMPLATE,
	TINTED_GLASS,
	TIPPED_ARROW,
	TNT,
	TNT_MINECART,
	TORCH,
	TORCHFLOWER,
	TORCHFLOWER_CROP,
	TORCHFLOWER_SEEDS,
	TOTEM_OF_UNDYING,
	TRADER_LLAMA_SPAWN_EGG,
	TRAPPED_CHEST,
	TRIDENT,
	TRIPWIRE,
	TRIPWIRE_HOOK,
	TROPICAL_FISH,
	TROPICAL_FISH_BUCKET,
	TROPICAL_FISH_SPAWN_EGG,
	TUBE_CORAL,
	TUBE_CORAL_BLOCK,
	TUBE_CORAL_FAN,
	TUBE_CORAL_WALL_FAN,
	TUFF,
	TURTLE_EGG,
	TURTLE_HELMET,
	TURTLE_SPAWN_EGG,
	TWISTING_VINES,
	TWISTING_VINES_PLANT,
	VERDANT_FROGLIGHT,
	VEX_ARMOR_TRIM_SMITHING_TEMPLATE,
	VEX_SPAWN_EGG,
	VILLAGER_SPAWN_EGG,
	VINDICATOR_SPAWN_EGG,
	VINE,
	VOID_AIR,
	WALL_TORCH,
	WANDERING_TRADER_SPAWN_EGG,
	WARDEN_SPAWN_EGG,
	WARD_ARMOR_TRIM_SMITHING_TEMPLATE,
	WARPED_BUTTON,
	WARPED_DOOR,
	WARPED_FENCE,
	WARPED_FENCE_GATE,
	WARPED_FUNGUS,
	WARPED_FUNGUS_ON_A_STICK,
	WARPED_HANGING_SIGN,
	WARPED_HYPHAE,
	WARPED_NYLIUM,
	WARPED_PLANKS,
	WARPED_PRESSURE_PLATE,
	WARPED_ROOTS,
	WARPED_SIGN,
	WARPED_SLAB,
	WARPED_STAIRS,
	WARPED_STEM,
	WARPED_TRAPDOOR,
	WARPED_WALL_HANGING_SIGN,
	WARPED_WALL_SIGN,
	WARPED_WART_BLOCK,
	WATER,
	WATER_BUCKET,
	WATER_CAULDRON,
	WAXED_COPPER_BLOCK,
	WAXED_CUT_COPPER,
	WAXED_CUT_COPPER_SLAB,
	WAXED_CUT_COPPER_STAIRS,
	WAXED_EXPOSED_COPPER,
	WAXED_EXPOSED_CUT_COPPER,
	WAXED_EXPOSED_CUT_COPPER_SLAB,
	WAXED_EXPOSED_CUT_COPPER_STAIRS,
	WAXED_OXIDIZED_COPPER,
	WAXED_OXIDIZED_CUT_COPPER,
	WAXED_OXIDIZED_CUT_COPPER_SLAB,
	WAXED_OXIDIZED_CUT_COPPER_STAIRS,
	WAXED_WEATHERED_COPPER,
	WAXED_WEATHERED_CUT_COPPER,
	WAXED_WEATHERED_CUT_COPPER_SLAB,
	WAXED_WEATHERED_CUT_COPPER_STAIRS,
	WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE,
	WEATHERED_COPPER,
	WEATHERED_CUT_COPPER,
	WEATHERED_CUT_COPPER_SLAB,
	WEATHERED_CUT_COPPER_STAIRS,
	WEEPING_VINES,
	WEEPING_VINES_PLANT,
	WET_SPONGE,
	WHEAT,
	WHEAT_SEEDS,
	WHITE_BANNER,
	WHITE_BED,
	WHITE_CANDLE,
	WHITE_CANDLE_CAKE,
	WHITE_CARPET,
	WHITE_CONCRETE,
	WHITE_CONCRETE_POWDER,
	WHITE_DYE,
	WHITE_GLAZED_TERRACOTTA,
	WHITE_SHULKER_BOX,
	WHITE_STAINED_GLASS,
	WHITE_STAINED_GLASS_PANE,
	WHITE_TERRACOTTA,
	WHITE_TULIP,
	WHITE_WALL_BANNER,
	WHITE_WOOL,
	WILD_ARMOR_TRIM_SMITHING_TEMPLATE,
	WITCH_SPAWN_EGG,
	WITHER_ROSE,
	WITHER_SKELETON_SKULL,
	WITHER_SKELETON_SPAWN_EGG,
	WITHER_SKELETON_WALL_SKULL,
	WITHER_SPAWN_EGG,
	WOLF_SPAWN_EGG,
	WOODEN_AXE,
	WOODEN_HOE,
	WOODEN_PICKAXE,
	WOODEN_SHOVEL,
	WOODEN_SWORD,
	WRITABLE_BOOK,
	WRITTEN_BOOK,
	YELLOW_BANNER,
	YELLOW_BED,
	YELLOW_CANDLE,
	YELLOW_CANDLE_CAKE,
	YELLOW_CARPET,
	YELLOW_CONCRETE,
	YELLOW_CONCRETE_POWDER,
	YELLOW_DYE,
	YELLOW_GLAZED_TERRACOTTA,
	YELLOW_SHULKER_BOX,
	YELLOW_STAINED_GLASS,
	YELLOW_STAINED_GLASS_PANE,
	YELLOW_TERRACOTTA,
	YELLOW_WALL_BANNER,
	YELLOW_WOOL,
	ZOGLIN_SPAWN_EGG,
	ZOMBIE_HEAD,
	ZOMBIE_HORSE_SPAWN_EGG,
	ZOMBIE_SPAWN_EGG,
	ZOMBIE_VILLAGER_SPAWN_EGG,
	ZOMBIE_WALL_HEAD,
	ZOMBIFIED_PIGLIN_SPAWN_EGG;
	
	static {
		REGISTRI_BY_IID = new ConcurrentHashMap<>();
	}
	
	private final NamespacedKey key;
	private final NamespacedKey clientKey;
	private final short itemID, blockStateID, blockID;
	private final byte max;
	private final float toughness, explosionResistance;
	private MetaDataFactory mdf;
	private TileEntityFactory tef;
	
	/**
	 * @param key the Material's name
	 * @param clientKey the Material's name on client
	 * @param itemID the protocol ItemID or -1 if it has no Item
	 * @param blockStateID the protocol BlockStateID or -1 if it has no Block
	 * @param blockID the protocol BlockID or -1 if it has no Block
	 * @param maxAmount
	 * @param toughness
	 * @param explosionResistance
	 * @param mdf null to use MetaDataFactory.DEFAULT
	 */
	public Material(NamespacedKey key, NamespacedKey clientKey, short itemID, short blockStateID, short blockID, byte maxAmount, float toughness, float explosionResistance, MetaDataFactory mdf) {
		if (key == null)
			throw new IllegalArgumentException("Key can not be null!");
		if (clientKey == null)
			throw new IllegalArgumentException("Client key can not be null!");
		this.key = key;
		this.clientKey = clientKey;
		this.itemID = itemID;
		this.blockStateID = blockStateID;
		this.blockID = blockID;
		this.max = maxAmount;
		this.toughness = toughness;
		this.explosionResistance = explosionResistance;
		registerMaterial();
		setMetaDataFactory(mdf);
	}
	
	public short getItemID() {
		return itemID;
	}
	
	public short getBlockStateID() {
		return blockStateID;
	}
	
	public short getBlockID() {
		return blockID;
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
		return mdf;
	}
	
	public void setMetaDataFactory(MetaDataFactory factory) {
		if (factory == null)
			factory = Registries.getInstanceDefault(MetaDataFactory.class);
		mdf = factory;
	}
	
	public TileEntityFactory getTileEntityFactory() {
		return tef;
	}
	
	public void setTileEntityFactory(TileEntityFactory factory) {
		this.tef = factory;
	}
	
	public ItemMeta createItemMeta() {
		return mdf.createMeta(this);
	}
	
	public BlockData createBlockData() {
		return mdf.createData(this);
	}
	
	public TileEntity createTileEntity() {
		TileEntityFactory tf = getTileEntityFactory();
		if (tf != null)
			return tf.createTile(this);
		return null;
	}
	
	public int getTileID() {
		return tef != null ? tef.getTileID() : -1;
	}
	
	public boolean isValidMeta(ItemMeta meta) {
		return mdf.isValidMeta(meta);
	}
	
	public boolean isValidData(BlockData data) {
		if (data.getMaterial() != this) 
			return false;
		return mdf.isValidData(data);
	}
	
	public boolean isValidTile(TileEntity tile) {
		if (!isBlock()) 
			return false;
		TileEntityFactory tf = getTileEntityFactory();
		return tf != null && tf.isValidTile(tile);
	}
	
	/**
	 * Returns true if this Material can be represented as an {@link ItemStack}
	 * @return true if item
	 */
	public boolean isItem() {
		return itemID != -1;
	}
	
	/**
	 * Returns true if this Material can be represented as a {@link BlockData}
	 * @return true if block
	 */
	public boolean isBlock() {
		return blockStateID != -1;
	}
	
	/**
	 * Internal registration method for a Material
	 */
	private final void registerMaterial() {
		final NamespacedKey key = getNamespacedKey();
		getRegistry().register(key, this);
		if (isItem())
			REGISTRI_BY_IID.put((int) getItemID(), this);
	}
	
	/**
	 * Method to remove a Material from registri
	 */
	public final void unregister() {
		final NamespacedKey key = getNamespacedKey();
		getRegistry().remove(key);
	}
	
	public static Registry<Material> getRegistry() {
		Registry<Material> r = registry;
		if (r == null) {
			synchronized (EntityType.class) {
				r = registry;
				if (r == null)
					r = registry = Registries.getInstanceRegistry(Material.class);
			}
		}
		return r;
	}
	
	/**
	 * Returns the max amount this Material stacks to by default.<br>
	 * However amounts in range of -128 to 127 are possible.
	 * @return max amount
	 */
	public int getMaxAmount() {
		return max;
	}
	
	/**
	 * Returns whether or not this Material is some kind of air e.g. {@link Material#AIR}
	 * @return true if air
	 */
	public boolean isAir() {
		return this == AIR || this == CAVE_AIR || this == VOID_AIR;
	}
	
	/**
	 * 
	 * @param name the materials name with or without the namespace
	 * @return first material with the name if no namespace if given
	 */
	public static Material getByName(String name) {
		if (name == null) 
			throw new IllegalArgumentException("Name can not be null!");
		final int index = name.indexOf(':');
		if (index == -1 || (index == NamespacedKey.MINECRAFT.length() && name.startsWith(NamespacedKey.MINECRAFT)))
			return getMaterial(NamespacedKey.MINECRAFT, name);
		return getRegistry().get(name);
	}
	
	/**
	 * Returns a Material by its namespace and name
	 * @param namespace
	 * @param name
	 * @return Material or null
	 */
	@Nullable
	public static Material getMaterial(@NotNull String namespace,@NotNull String name) {
		if (namespace == null) 
			throw new IllegalArgumentException("Namespace can not be null!");
		if (name == null)
			throw new IllegalArgumentException("Name can not be null!");
		return getRegistry().get(NamespacedKey.of(namespace, name));
	}
	
	/**
	 * Returns a Material by its {@link NamespacedKey}
	 * @param key
	 * @return Material or null
	 */
	@Nullable
	public static Material getMaterial(@NotNull NamespacedKey key) {
		if (key == null)
			throw new IllegalArgumentException("Key can not be null!");
		return getRegistry().get(key);
	}

	@Override
	public NamespacedKey getNamespacedKey() {
		return key;
	}
	
	/**
	 * Returns the NamespacedKey used to send to the client
	 * @return NamespacedKey
	 */
	public NamespacedKey getClientKey() {
		return clientKey;
	}

	/**
	 * Returns a Material by its ItemID
	 * @param itemID of a Material
	 * @return Material or null
	 */
	@Nullable
	public static Material getByItemID(int itemID) {
		return REGISTRI_BY_IID.get(itemID);
	}
	
}
