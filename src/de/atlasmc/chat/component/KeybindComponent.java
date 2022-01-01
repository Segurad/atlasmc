package de.atlasmc.chat.component;

public class KeybindComponent extends AbstractComponent {
	
	public static final String
	KEY_ATTACK = "key.attack",
	KEY_USE = "key.use",
	KEY_FORWARD = "key.forward",
	KEY_LEFT = "key.left",
	KEY_BACK = "key.back",
	KEY_RIGHT = "key.right",
	KEY_JUMP = "key.jump",
	KEY_SNEAK = "key.sneak",
	KEY_SPRINT = "key.sprint",
	KEY_DROP = "key.drop",
	KEY_INVENTORY = "key.inventory",
	KEY_CHAT = "key.chat",
	KEY_PLAYERLIST = "key.playerlist",
	KEY_PICK_ITEM = "key.pickItem",
	KEY_COMMAND = "key.command",
	KEY_SOCIAL_INTERACTIONS = "key.socialInteractions",
	KEY_SCREENSHOT = "key.screenshot",
	KEY_TOGGLE_PERSPECTIVE = "key.togglePerspective",
	KEY_SMOOTH_CAMERA = "key.smoothCamera",
	KEY_FULLSCREEN = "key.fullscreen",
	KEY_SPECTATOR_OUTLINES = "key.spectatorOutlines",
	KEY_SWAP_OFFHAND = "key.swapOffhand",
	KEY_SAVE_TOOLBAR_ACTIVATOR = "key.saveToolbarActivator",
	KEY_LOAD_TOOLBAR_ACTIVATOR = "key.loadToolbarActivator",
	KEY_ADVANCEMENTS = "key.advancements",
	KEY_HOTBAR_1 = "key.hotbar.1",
	KEY_HOTBAR_2 = "key.hotbar.2",
	KEY_HOTBAR_3 = "key.hotbar.3",
	KEY_HOTBAR_4 = "key.hotbar.4",
	KEY_HOTBAR_5 = "key.hotbar.5",
	KEY_HOTBAR_6 = "key.hotbar.6",
	KEY_HOTBAR_7 = "key.hotbar.7",
	KEY_HOTBAR_8 = "key.hotbar.8",
	KEY_HOTBAR_9 = "key.hotbar.9";
	
	private String key;

	@Override
	public String getLegacyText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJsonText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(ChatComponent text) {
		// TODO Auto-generated method stub
		return false;
	}

}
