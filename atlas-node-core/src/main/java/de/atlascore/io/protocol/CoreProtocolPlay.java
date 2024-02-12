package de.atlascore.io.protocol;

import de.atlascore.io.protocol.play.*;
import de.atlasmc.io.PacketIO;
import de.atlasmc.io.PacketListener;
import de.atlasmc.io.protocol.PlayerConnection;
import de.atlasmc.io.protocol.ProtocolPlay;
import de.atlasmc.io.protocol.play.PacketPlayIn;
import de.atlasmc.io.protocol.play.PacketPlayOut;

public class CoreProtocolPlay extends CoreAbstractProtocol<PacketPlayIn, PacketPlayOut> implements ProtocolPlay {
	
	@SuppressWarnings("unchecked")
	public CoreProtocolPlay() {
		super(new PacketIO[] {
				new CorePacketInConfirmTeleport(),
				new CorePacketInQueryBlockEntityTag(),
				new CorePacketInChangeDifficulty(),
				new CorePacketInChatMessage(),
				new CorePacketInClientCommand(),
				new CorePacketInClientInformation(),
				new CorePacketInCommandSuggestionsRequest(),
				new CorePacketInClickContainerButton(),
				new CorePacketInClickWindow(),
				new CorePacketInCloseContainer(),
				new CorePacketInPluginMessage(),
				new CorePacketInEditBook(),
				new CorePacketInQueryEntityTag(),
				new CorePacketInInteract(),
				new CorePacketInJigsawGenerate(),
				new CorePacketInKeepAlive(),
				new CorePacketInLockDifficulty(),
				new CorePacketInSetPlayerPosition(),
				new CorePacketInSetPlayerPositionAndRotation(),
				new CorePacketInSetPlayerRotation(),
				new CorePacketInSetPlayerOnGround(),
				new CorePacketInMoveVehice(),
				new CorePacketInPaddleBoat(),
				new CorePacketInPickItem(),
				new CorePacketInPlaceRecipe(),
				new CorePacketInPlayerAbilities(),
				new CorePacketInPlayerAction(),
				new CorePacketInPlayerCommand(),
				new CorePacketInPlayerInput(),
				new CorePacketInChangeRecipeBookSettings(),
				new CorePacketInSetSeenRecipe(),
				new CorePacketInRenameItem(),
				new CorePacketInResourcePack(),
				new CorePacketInSeenAdvancements(),
				new CorePacketInSelectTrade(),
				new CorePacketInSetBeaconEffect(),
				new CorePacketInSetHeldItem(),
				new CorePacketInProgramCommandBlock(),
				new CorePacketInProgramCommandBlockMinecart(),
				new CorePacketInSetCreativeModeSlot(),
				new CorePacketInProgramJigsawBlock(),
				new CorePacketInProgramStructureBlock(),
				new CorePacketInUpdateSign(),
				new CorePacketInSwingArm(),
				new CorePacketInTeleportToEntity(),
				new CorePacketInUseItemOn(),
				new CorePacketInUseItem(),
				new CorePacketInMessageAcknowledgment(),
				new CorePacketInPong(),
				new CorePacketInChatCommand(),
				new CorePacketInPlayerSession(),
				new CorePacketInChunkBatchReceived(),
				new CorePacketInConfigurationAcknowledged(),
				new CorePacketInPingRequest()
		}, new PacketIO[] {
				new CorePacketOutSpawnEntity(),
				new CorePacketOutSpawnExperienceOrb(),
				new CorePacketOutEntityAnimation(),
				new CorePacketOutAwardStatistics(),
				new CorePacketOutSetBlockDestroyStage(),
				new CorePacketOutBlockEntityData(),
				new CorePacketOutBlockAction(),
				new CorePacketOutBlockUpdate(),
				new CorePacketOutBossBar(),
				new CorePacketOutChangeDifficulty(),
				new CorePacketOutSystemChatMessage(),
				new CorePacketOutCommandSuggestionsResponse(),
				new CorePacketOutCommands(),
				new CorePacketOutCloseContainer(),
				new CorePacketOutSetContainerContents(),
				new CorePacketOutSetContainerProperty(),
				new CorePacketOutSetContainerSlot(),
				new CorePacketOutSetCooldown(),
				new CorePacketOutPluginMessage(),
				new CorePacketOutDisconnect(),
				new CorePacketOutEntityEvent(),
				new CorePacketOutExplosion(),
				new CorePacketOutUnloadChunk(),
				new CorePacketOutGameEvent(),
				new CorePacketOutOpenHorseScreen(),
				new CorePacketOutKeepAlive(),
				new CorePacketOutChunkData(),
				new CorePacketOutWorldEvent(),
				new CorePacketOutParticle(),
				new CorePacketOutUpdateLight(),
				new CorePacketOutLogin(),
				new CorePacketOutMapData(),
				new CorePacketOutMerchantOffers(),
				new CorePacketOutUpdateEntityPosition(),
				new CorePacketOutEntityUpdatePositionAndRotation(),
				new CorePacketOutUpdateEntityRotation(),
				new CorePacketOutMoveVehicle(),
				new CorePacketOutOpenBook(),
				new CorePacketOutOpenScreen(),
				new CorePacketOutOpenSignEditor(),
				new CorePacketOutPlayerAbilities(),
				new CorePacketOutPlayerInfoUpdate(),
				new CorePacketOutLookAt(),
				new CorePacketOutSynchronizePlayerPosition(),
				new CorePacketOutUpdateRecipeBook(),
				new CorePacketOutRemoveEntities(),
				new CorePacketOutRemoveEntityEffect(),
				new CorePacketOutRessourcePack(),
				new CorePacketOutRespawn(),
				new CorePacketOutSetHeadRotation(),
				new CorePacketOutUpdateSectionBlocks(),
				new CorePacketOutSetCamera(),
				new CorePacketOutSetHeldItem(),
				new CorePacketOutSetCenterChunk(),
				new CorePacketOutRenderDistance(),
				new CorePacketOutSetDefaultSpawnPosition(),
				new CorePacketOutDisplayObjective(),
				new CorePacketOutSetEntityMetadata(),
				new CorePacketOutLinkEntities(),
				new CorePacketOutSetEntityVelocity(),
				new CorePacketOutSetEquipment(),
				new CorePacketOutSetHealth(),
				new CorePacketOutUpdateObjectives(),
				new CorePacketOutSetPassengers(),
				new CorePacketOutUpdateTeams(),
				new CorePacketOutUpdateScore(),
				new CorePacketOutUpdateTime(),
				new CorePacketOutSetTitleAnimationTimes(),
				new CorePacketOutEntitySoundEffect(),
				new CorePacketOutSoundEffect(),
				new CorePacketOutStopSound(),
				new CorePacketOutSetTabListHeaderAndFooter(),
				new CorePacketOutTagQueryResponse(),
				new CorePacketOutPickupItem(),
				new CorePacketOutEntityTeleport(),
				new CorePacketOutUpdateAdvancements(),
				new CorePacketOutUpdateAttributes(),
				new CorePacketOutEntityEffect(),
				new CorePacketOutUpdateRecipes(),
				new CorePacketOutUpdateTags(),
				new CorePacketOutBundleDelimiter(),
				new CorePacketOutAcknowledgeBlockChange(),
				new CorePacketOutChunkBatchFinished(),
				new CorePacketOutChunkBatchStart(),
				new CorePacketOutChunkBiomes(),
				new CorePacketOutClearTitle(),
				new CorePacketOutChatSuggestions(),
				new CorePacketOutDamageEvent(),
				new CorePacketOutDeleteMessage(),
				new CorePacketOutDisguisedChatMessage(),
				new CorePacketOutHurtAnimation(),
				new CorePacketOutInitializeWorldBorder(),
				new CorePacketOutPing(),
				new CorePacketOutPingResponse(),
				new CorePacketOutPlaceGhostRecipe(),
				new CorePacketOutChatMessage(),
				new CorePacketOutEndCombat(),
				new CorePacketOutEnterCombat(),
				new CorePacketOutCombatDeath(),
				new CorePacketOutPlayerInfoRemove(),
				new CorePacketOutSelectAdvancementTab(),
				new CorePacketOutServerData(),
				new CorePacketOutSetActionBarText(),
				new CorePacketOutSetBorderCenter(),
				new CorePacketOutSetBorderLerpSize(),
				new CorePacketOutSetBorderSize(),
				new CorePacketOutSetBorderWarningDistance(),
				new CorePacketOutSetBorderWarningDelay(),
				new CorePacketOutSetExperience(),
				new CorePacketOutSetSimulationDistance(),
				new CorePacketOutSetSubtitleText(),
				new CorePacketOutSetTitleText(),
				new CorePacketOutStartConfiguration()
		});
	}

	@Override
	public PacketListener createDefaultPacketListener(Object o) {
		if (o instanceof PlayerConnection con)
			return new CorePacketListenerPlayIn(con);
		throw new IllegalArgumentException("Expected PlayerConnection but got: " + o.getClass().getName());
	}

}
