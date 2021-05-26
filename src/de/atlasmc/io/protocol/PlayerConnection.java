package de.atlasmc.io.protocol;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

import de.atlascore.io.protocol.CorePacketListenerPlay;
import de.atlasmc.Atlas;
import de.atlasmc.atlasnetwork.server.AtlasPlayer;
import de.atlasmc.atlasnetwork.server.LocalServer;
import de.atlasmc.entity.Player;
import de.atlasmc.event.Event;
import de.atlasmc.event.HandlerList;
import de.atlasmc.event.inventory.ClickType;
import de.atlasmc.event.inventory.InventoryButtonType;
import de.atlasmc.event.inventory.InventoryClickButtonEvent;
import de.atlasmc.event.inventory.InventoryClickEvent;
import de.atlasmc.event.inventory.InventoryCloseEvent;
import de.atlasmc.event.inventory.InventoryType;
import de.atlasmc.event.player.AsyncPlayerChatEvent;
import de.atlasmc.event.player.PlayerRespawnEvent;
import de.atlasmc.inventory.InventoryView;
import de.atlasmc.io.ConnectionHandler;
import de.atlasmc.io.Packet;
import de.atlasmc.io.protocol.play.PacketInAdvancementTab;
import de.atlasmc.io.protocol.play.PacketInAnimation;
import de.atlasmc.io.protocol.play.PacketInChatMessage;
import de.atlasmc.io.protocol.play.PacketInClickWindow;
import de.atlasmc.io.protocol.play.PacketInClickWindowButton;
import de.atlasmc.io.protocol.play.PacketInClientSettings;
import de.atlasmc.io.protocol.play.PacketInClientStatus;
import de.atlasmc.io.protocol.play.PacketInClientStatus.StatusAction;
import de.atlasmc.util.annotation.ThreadSafe;
import de.atlasmc.io.protocol.play.PacketInCloseWindow;
import de.atlasmc.io.protocol.play.PacketInCraftRecipeRequest;
import de.atlasmc.io.protocol.play.PacketInCreativeInventoryAction;
import de.atlasmc.io.protocol.play.PacketInEditBook;
import de.atlasmc.io.protocol.play.PacketInEntityAction;
import de.atlasmc.io.protocol.play.PacketInGenerateStructure;
import de.atlasmc.io.protocol.play.PacketInHeldItemChange;
import de.atlasmc.io.protocol.play.PacketInInteractEntity;
import de.atlasmc.io.protocol.play.PacketInKeepAlive;
import de.atlasmc.io.protocol.play.PacketInLockDifficulty;
import de.atlasmc.io.protocol.play.PacketInNameItem;
import de.atlasmc.io.protocol.play.PacketInPickItem;
import de.atlasmc.io.protocol.play.PacketInPlayerAbilities;
import de.atlasmc.io.protocol.play.PacketInPlayerBlockPlacement;
import de.atlasmc.io.protocol.play.PacketInPlayerDigging;
import de.atlasmc.io.protocol.play.PacketInPlayerMovement;
import de.atlasmc.io.protocol.play.PacketInPlayerPosition;
import de.atlasmc.io.protocol.play.PacketInPlayerPositionAndRotation;
import de.atlasmc.io.protocol.play.PacketInPlayerRotation;
import de.atlasmc.io.protocol.play.PacketInPluginMessage;
import de.atlasmc.io.protocol.play.PacketInQueryBlockNBT;
import de.atlasmc.io.protocol.play.PacketInQueryEntityNBT;
import de.atlasmc.io.protocol.play.PacketInResourcePackStatus;
import de.atlasmc.io.protocol.play.PacketInSelectTrade;
import de.atlasmc.io.protocol.play.PacketInSetBeaconEffect;
import de.atlasmc.io.protocol.play.PacketInSetDifficulty;
import de.atlasmc.io.protocol.play.PacketInSetDisplayedRecipe;
import de.atlasmc.io.protocol.play.PacketInSetRecipeBookState;
import de.atlasmc.io.protocol.play.PacketInSpectate;
import de.atlasmc.io.protocol.play.PacketInSteerBoat;
import de.atlasmc.io.protocol.play.PacketInSteerVehicle;
import de.atlasmc.io.protocol.play.PacketInTabComplete;
import de.atlasmc.io.protocol.play.PacketInTeleportConfirm;
import de.atlasmc.io.protocol.play.PacketInUpdateCommandBlock;
import de.atlasmc.io.protocol.play.PacketInUpdateCommandBlockMinecart;
import de.atlasmc.io.protocol.play.PacketInUpdateJigsawBlock;
import de.atlasmc.io.protocol.play.PacketInUpdateSign;
import de.atlasmc.io.protocol.play.PacketInUpdateStructureBlock;
import de.atlasmc.io.protocol.play.PacketInUseItem;
import de.atlasmc.io.protocol.play.PacketInVehicleMove;
import de.atlasmc.io.protocol.play.PacketInWindowConfirmation;

public class PlayerConnection implements PacketListenerPlayIn {
	
	private Player player;
	private final AtlasPlayer aplayer;
	private final ConnectionHandler connection;
	private final ProtocolAdapter protocol;
	private LocalServer server;
	private String local;
	private byte viewDistance, skinparts, mainHand;
	private boolean chatColors = true;
	private final ConcurrentLinkedQueue<Packet> inboundQueue;
	
	private boolean ignoreClick;
	private short confirmNumber;
	private byte invID;
	
	public PlayerConnection(AtlasPlayer player, ConnectionHandler connection, ProtocolAdapter protocol) {
		this.aplayer = player;
		this.connection = connection;
		this.protocol = protocol;
		this.inboundQueue = new ConcurrentLinkedQueue<>();
	}
	
	public void queueInboundPacket(Packet packet) {
		inboundQueue.add(packet);
	}
	
	public void handleQueuedPackets() {
		while (!inboundQueue.isEmpty()) {
			Packet packet = inboundQueue.poll();
			CorePacketListenerPlay.handlePacket(player, packet);
		}
	}

	@Override
	public void handlePacket(PacketInTeleportConfirm packet) {
		// TODO
	}

	@Override
	public void handlePacket(PacketInQueryBlockNBT packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInQueryEntityNBT packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInSetDifficulty packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInChatMessage packet) {
		HandlerList.callEvent(new AsyncPlayerChatEvent(false, player, packet.getMessage()));
	}

	@Override
	public void handlePacket(PacketInClientStatus packet) {
		if (packet.getAction() == StatusAction.RESPAWN) {
			HandlerList.callEvent(new PlayerRespawnEvent(player));
		}
	}

	@Override
	public void handlePacket(PacketInClientSettings packet) {
		chatColors = packet.getChatColor();
		// TODO packet.getChatMode();
		skinparts = packet.getDisplaySkinParts();
		local = packet.getLocale();
		mainHand = (byte) packet.getMainHand();
		viewDistance = (byte) packet.getViewDistance();
	}

	@Override
	public void handlePacket(PacketInTabComplete packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInWindowConfirmation packet) {
		if (ignoreClick) {
			if (confirmNumber == packet.getActionNumber()) {
				ignoreClick = false;
			}
		}
	}

	@Override
	public void handlePacket(PacketInClickWindowButton packet) {
		LocalServer s = getLocalSever();
		if (s == null) return;
		InventoryView inv = player.getOpenInventory();
		InventoryButtonType type = null;
		int id = packet.getButtonID();
		if (inv.getType() == InventoryType.ENCHANTING) {
			switch (id) {
			case 0: type = InventoryButtonType.ENCHANTING_TOP_ENCHANTMENT; break;
			case 1: type = InventoryButtonType.ENCHANTING_MIDDLE_ENCHANTMENT; break;
			case 2: type = InventoryButtonType.ENCHANTING_BOTTOM_EMCHANTMENT; break;
			}
			id = -1;
		} else if (inv.getType() == InventoryType.LECTERN) {
			switch (id) {
			case 1: type = InventoryButtonType.LECTERN_PREVIOUS_PAGE; break;
			case 2: type = InventoryButtonType.LECTERN_NEXT_PAGE; break;
			case 3: type = InventoryButtonType.LECTERN_TAKE_BOOK; break;
			default: type = InventoryButtonType.LECTERN_OPEN_PAGE_NUMBER; break;
			}
			if (id < 100) {
				id = -1;
			} else id -= 100;
		} else if (inv.getType() == InventoryType.STONECUTTER) {
			type = InventoryButtonType.STONECUTTER_RECIPE_NUMBER;
		} else if (inv.getType() == InventoryType.LOOM) {
			type = InventoryButtonType.LOOM_RECIPE_NUMBER;
		}
		HandlerList.callEvent(new InventoryClickButtonEvent(player.getOpenInventory(), type, id));
	}
	
	@Override
	public void handlePacket(PacketInClickWindow packet) {
		if (ignoreClick) {
			return;
		}
		LocalServer s = getLocalSever();
		if (s == null) return;
		ClickType click = null;
		int key = -1;
		int slot = packet.getSlot();
		int button = packet.getButton();
		if (packet.getMode() != 5) {
			switch (packet.getMode()) {
			case 0: 
				if (button == 0) {
					click = ClickType.LEFT;
				} else {
					click = ClickType.RIGHT;
				}
				break;
			case 1:
				if (button == 0) {
					click = ClickType.SHIFT_LEFT;
				} else {
					click = ClickType.SHIFT_RIGHT;
				}
				break;
			case 2:
				key = packet.getButton();
				click = ClickType.NUMBER_KEY;
				break;
			case 3:
				click = ClickType.MIDDLE;
				break;
			case 4:
				if (slot != -999) {
					if (button == 0) {
						click = ClickType.DROP;
					} else {
						click = ClickType.CONTROL_DROP;
					}
				} else if (button == 0) {
					click = ClickType.WINDOW_BORDER_LEFT;
				} else {
					click = ClickType.WINDOW_BORDER_RIGHT;
				}
				break;
			case 6:
				click = ClickType.DOUBLE_CLICK;
				break;
			}
			HandlerList.callEvent(new InventoryClickEvent(player.getOpenInventory(), slot, click, action, key));
		} else {
			switch (button) {
			// TODO
			case 0:
				// Start drag left
			case 4:
				// Start drag right
			case 8:
				// Start middle mouse drag
			case 1:
				// add slot left drag
			case 5:
				// add slot right drag
			case 9:
				// add slot middle drag
			case 2:
				// ending left drag
			case 6:
				// ending right drag 
			case 10:
				// ending middle drag
				break;
			}
		}
	}

	@Override
	public void handlePacket(PacketInCloseWindow packet) {
		LocalServer s = getLocalSever();
		if (s == null) return;
		HandlerList.callEvent(new InventoryCloseEvent(player.getOpenInventory()));
	}

	@Override
	public void handlePacket(PacketInPluginMessage packet) {
		Atlas.getMessenger().handleInboundMessage(player, packet.getChannel(), packet.getData());
	}
	
	@Override
	public void handlePacket(PacketInEditBook packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInInteractEntity packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInGenerateStructure packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInKeepAlive packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInLockDifficulty packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInPlayerPosition packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInPlayerPositionAndRotation packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInPlayerRotation packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInPlayerMovement packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInVehicleMove packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInSteerBoat packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInPickItem packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInCraftRecipeRequest packet) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void handlePacket(PacketInPlayerAbilities packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInPlayerDigging packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInEntityAction packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInSteerVehicle packet) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void handlePacket(PacketInSetRecipeBookState packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInSetDisplayedRecipe packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInNameItem packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInResourcePackStatus packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInAdvancementTab packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInSelectTrade packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInSetBeaconEffect packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInHeldItemChange packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInUpdateCommandBlock packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInUpdateCommandBlockMinecart packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInCreativeInventoryAction packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInUpdateJigsawBlock packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInUpdateStructureBlock packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInUpdateSign packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInAnimation packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInSpectate packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInPlayerBlockPlacement packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePacket(PacketInUseItem packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleUnhandledPacket(Packet packet) {
		// TODO Auto-generated method stub
		
	}

	public ProtocolAdapter getProtocol() {
		return protocol;
	}
	
	/**
	 * 
	 * @return the current inventory id
	 */
	public int getInventoryID() {
		return invID;
	}
	
	/**
	 * increments and returns the inventory id
	 * @return the new current inventory id
	 */
	public int getNextInventoryID() {
		confirmNumber = 0;
		return ++invID;
	}

	/**
	 * 
	 * @return the current server or null if not at this note
	 */
	@ThreadSafe
	public synchronized LocalServer getLocalSever() {
		return server;
	}
	
	public int getNextWindowActionID() {
		return confirmNumber;
	}
	
	public int getNextWindowActionIDAndLock() {
		ignoreClick = true;
		return confirmNumber++;
	}

}