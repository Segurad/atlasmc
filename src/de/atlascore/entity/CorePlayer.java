package de.atlascore.entity;

import java.util.UUID;

import de.atlascore.inventory.CoreInventoryView;
import de.atlasmc.Effect;
import de.atlasmc.Gamemode;
import de.atlasmc.Location;
import de.atlasmc.Particle;
import de.atlasmc.Sound;
import de.atlasmc.SoundCategory;
import de.atlasmc.atlasnetwork.AtlasPlayer;
import de.atlasmc.entity.EntityType;
import de.atlasmc.entity.Player;
import de.atlasmc.inventory.Inventory;
import de.atlasmc.inventory.InventoryView;
import de.atlasmc.inventory.ItemStack;
import de.atlasmc.io.protocol.PlayerConnection;
import de.atlasmc.io.protocol.play.PacketOutChangeGameState;
import de.atlasmc.io.protocol.play.PacketOutCloseWindow;
import de.atlasmc.io.protocol.play.PacketOutOpenWindow;
import de.atlasmc.io.protocol.play.PacketOutChangeGameState.ChangeReason;
import de.atlasmc.io.protocol.play.PacketOutSetExperiance;
import de.atlasmc.io.protocol.play.PacketOutSetSlot;
import de.atlasmc.scoreboard.Scoreboard;

public class CorePlayer extends CoreHumanEntity implements Player {

	private PlayerConnection con;
	private Inventory open;
	private int level;
	private Gamemode gamemode;
	private CoreInventoryView view;
	private ItemStack cursorItem;
	private boolean canBuild;
	
	public CorePlayer(int id, EntityType type, Location loc, UUID uuid) {
		super(id, type, loc, uuid);
		view = new CoreInventoryView(this, getInventory(), getInventory().getCraftingInventory(), 0);
	}
	
	@Override
	public void openInventory(Inventory inv) {
		if (inv == null) throw new IllegalArgumentException("Inventory can not be null!");
		if (inv == getInventory()) throw new IllegalArgumentException("Can not open own Inventory!");
		if (inv.getType().getID() == -1) throw new IllegalArgumentException("Can not open Inventory with this type (Please use the dedicated method!): " + inv.getType().name());
		if (open != null) closeInventory();
		this.open = inv;
		view.setTopInventory(open);
		view.setViewID(con.getNextInventoryID());
		PacketOutOpenWindow packet = con.getProtocol().createPacket(PacketOutOpenWindow.class);
		packet.setWindowID(view.getViewID());
		packet.setWindowType(inv.getType());
		packet.setTitle(inv.getTitle().getJsonText());
		con.sendPacked(packet);
		inv.updateSlots(this);
	}

	@Override
	public boolean hasPermission(String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void playSound(Location location, Sound sound, int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
		PacketOutSetExperiance setXP = con.getProtocol().createPacket(PacketOutSetExperiance.class);
		setXP.setLevel(level);
		con.sendPacked(setXP);
	}

	@Override
	public void playEffect(Location loc, Effect effect, Object data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spawnParticle(Particle particle, Location loc, int amount, int i, int j, int k, int l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSound(Location loc, Sound sound, SoundCategory category, float volume, float pitch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSound(Location loc, String ssound, SoundCategory category, float volume, float pitch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		open.getViewers().remove(this);
		open = null;
		view.setTopInventory(getInventory().getCraftingInventory());
		view.setViewID(0);
		PacketOutCloseWindow packet = con.getProtocol().createPacket(PacketOutCloseWindow.class);
		packet.setWindowID(con.getInventoryID());
		con.sendPacked(packet);
	}

	@Override
	public void setItemOnCursor(ItemStack item) {
		this.cursorItem = item;
	}

	@Override
	public ItemStack getItemOnCursor() {
		return cursorItem;
	}

	@Override
	public PlayerConnection getConnection() {
		return con;
	}

	@Override
	public AtlasPlayer getAtlasPlayer() {
		return con.getAtlasPlayer();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setScoreboard(Scoreboard board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UUID getInternalUUID() {
		return con.getAtlasPlayer().getInteranlUUID();
	}

	@Override
	public boolean hasInternalUUID() {
		return con.getAtlasPlayer().hasInternalUUID();
	}

	@Override
	public void setInternalUUID(UUID uuid) {
		con.getAtlasPlayer().setInternalUUID(uuid);
	}

	@Override
	public String getLocal() {
		return con.getClientLocal();
	}

	@Override
	public InventoryView getOpenInventory() {
		return view;
	}

	@Override
	public Gamemode getGamemode() {
		return gamemode;
	}

	@Override
	public boolean getCanBuild() {
		return canBuild;
	}

	@Override
	public void setCanBuild(boolean canBuild) {
		this.canBuild = canBuild;
	}

	@Override
	public void setGamemode(Gamemode gamemode) {
		this.gamemode = gamemode;
		PacketOutChangeGameState packet = con.getProtocol().createPacket(PacketOutChangeGameState.class);
		packet.setReason(ChangeReason.CHANGE_GAMEMODE);
		packet.setValue(gamemode.ordinal());
		con.sendPacked(packet);
	}

	@Override
	public void updateItemOnCursor() {
		PacketOutSetSlot packet = con.getProtocol().createPacket(PacketOutSetSlot.class);
		packet.setWindowID(-1);
		packet.setSlot(-1);
		packet.setItem(cursorItem);
	}

}
