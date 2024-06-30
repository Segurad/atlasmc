package de.atlascore.world;

import java.io.File;
import java.util.Collection;
import de.atlasmc.WorldEvent;
import de.atlasmc.Location;
import de.atlasmc.Particle;
import de.atlasmc.SimpleLocation;
import de.atlasmc.Sound;
import de.atlasmc.SoundCategory;
import de.atlasmc.block.Block;
import de.atlasmc.block.data.BlockData;
import de.atlasmc.entity.Entity;
import de.atlasmc.entity.EntityType;
import de.atlasmc.entity.Player;
import de.atlasmc.event.HandlerList;
import de.atlasmc.event.entity.EntitySpawnEvent;
import de.atlasmc.io.protocol.play.PacketOutWorldEvent;
import de.atlasmc.server.LocalServer;
import de.atlasmc.io.protocol.play.PacketOutEntitySoundEffect;
import de.atlasmc.io.protocol.play.PacketOutParticle;
import de.atlasmc.io.protocol.play.PacketOutSoundEffect;
import de.atlasmc.util.MathUtil;
import de.atlasmc.util.configuration.ConfigurationSection;
import de.atlasmc.world.Chunk;
import de.atlasmc.world.ChunkFactory;
import de.atlasmc.world.ChunkGenerator;
import de.atlasmc.world.ChunkGeneratorFactory;
import de.atlasmc.world.ChunkListener;
import de.atlasmc.world.ChunkLoader;
import de.atlasmc.world.ChunkLoaderFactory;
import de.atlasmc.world.ChunkProvider;
import de.atlasmc.world.ChunkProviderFactory;
import de.atlasmc.world.Dimension;
import de.atlasmc.world.PlayerChunkListener;
import de.atlasmc.world.World;
import de.atlasmc.world.WorldBuilder;
import de.atlasmc.world.WorldFlag;

public class CoreWorld implements World {
	
	private final ChunkProvider chunks;
	private final String name;
	private final LocalServer server;
	private final Dimension dimension;
	private long time;
	private boolean timeCycle;
	private long age;
	private int nextEntityID;
	private Location spawn;
	
	public CoreWorld(WorldBuilder builder) {
		if (builder == null)
			throw new IllegalArgumentException("Builder can not be null!");
		this.name = builder.getName();
		this.server = builder.getServer();
		this.dimension = builder.getDimension();
		if (server == null)
			throw new IllegalStateException("Server can not be null!");
		if (name == null)
			throw new IllegalStateException("Name can not be null!");
		if (dimension == null)
			throw new IllegalStateException("Dimension can not be null!");
		ChunkFactory chunkFactory = builder.getChunkFactory();
		ConfigurationSection chunkGenConfig = builder.getChunkGeneratorConfig();
		ChunkGeneratorFactory chunkGenFactory = builder.getChunkGenerator();
		ChunkGenerator chunkGen = null;
		if (chunkGenFactory != null)
			 chunkGenFactory.createChunkGenerator(this, chunkFactory, chunkGenConfig);
		File worldDir = builder.getWorldDir();
		ConfigurationSection chunkLoaderConfig = builder.getChunkLoaderConfig();
		ChunkLoaderFactory chunkLoaderFactory = builder.getChunkLoader();
		ChunkLoader chunkLoader = null;
		if (chunkLoaderFactory != null)
			chunkLoader = chunkLoaderFactory.createChunkLoader(this, worldDir, chunkFactory, chunkLoaderConfig);
		ConfigurationSection providerCfg = builder.getChunkProviderConfig();
		ChunkProviderFactory chunkProviderFactory = builder.getChunkProviderFactory();
		if (chunkProviderFactory == null)
			throw new IllegalStateException("ChunkProviderFactoy can not be null!");
		this.chunks = chunkProviderFactory.createProvider(this, chunkGen, chunkLoader, providerCfg);
		if (chunks == null)
			throw new IllegalStateException("No chunk provider initialized!");
	}

	@Override
	public Collection<Entity> getEntities() {
		return chunks.getEntities();
	}

	@Override
	public <T extends Entity> Collection<T> getEntitiesByClass(Class<T> clazz) {
		return chunks.getEntitiesByClass(clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Entity> getEntitesByClasses(Class<? extends Entity>... classes) {
		return chunks.getEntitesByClasses(classes);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public LocalServer getServer() {
		return server;
	}

	@Override
	public Block getHighestBlockAt(int x, int z) {
		return getBlock(x, getHighestBlockYAt(x, z), z);
	}

	@Override
	public int getHighestBlockYAt(int x, int z) {
		return chunks.getHighestBlockYAt(x, z);
	}

	@Override
	public long getTime() {
		return time;
	}

	@Override
	public void playEffect(SimpleLocation loc, WorldEvent effect, Object data, int radius) {
		if (loc == null)
			throw new IllegalArgumentException("Location can not be null!");
		if (effect == null)
			throw new IllegalArgumentException("Effect can not be null!");
		Chunk chunk = getChunk(loc, false);
		if (chunk == null)
			return;
		PacketOutWorldEvent packet = null;
		for (ChunkListener listener : chunk.getViewers()) {
			if (!(listener instanceof PlayerChunkListener))
				continue;
			PlayerChunkListener player = (PlayerChunkListener) listener;
			if (packet == null) {
				packet = new PacketOutWorldEvent();
				packet.setEvent(effect);
				packet.setPosition(MathUtil.toPosition(loc));
				packet.setData(effect.getDataValueByObject(data));
			}
			player.getConnection().sendPacked(packet);
		}
	}

	@Override
	public void spawnParticle(Particle particle, SimpleLocation loc, int amount) {
		if (loc == null)
			throw new IllegalArgumentException("Location can not be null!");
		if (particle == null)
			throw new IllegalArgumentException("Particle can not be null!");
		PacketOutParticle packet = null;
		Chunk chunk = getChunk(loc, false);
		if (chunk == null)
			return;
		for (ChunkListener listener : chunk.getViewers()) {
			if (!(listener instanceof PlayerChunkListener))
				continue;
			PlayerChunkListener player = (PlayerChunkListener) listener;
			if (packet == null) {
				packet = new PacketOutParticle();
				packet.setParticle(particle);
				packet.setLocation(loc.x, loc.y, loc.z);
				packet.setCount(amount);
			}
			player.getConnection().sendPacked(packet);
		}
	}

	@Override
	public Location getSpawnLocation() {
		return spawn.clone();
	}
	
	@Override
	public Location getSpawnLocation(Location loc) {
		return spawn.copyTo(loc);
	}

	@Override
	public void playSound(SimpleLocation loc, Sound sound, SoundCategory category, float volume, float pitch, long seed) {
		playSound(loc, sound, null, category, volume, pitch, seed, false, 0);
	}

	@Override
	public void playSound(SimpleLocation loc, String sound, SoundCategory category, float volume, float pitch, long seed, boolean fixedRange, float range) {
		playSound(loc, null, sound, category, volume, pitch, seed, fixedRange, range);
	}
	
	private void playSound(SimpleLocation loc, Sound sound, String identifier, SoundCategory category, float volume, float pitch, long seed, boolean fixedRange, float range) {
		if (loc == null)
			throw new IllegalArgumentException("Location can not be null!");
		if (sound == null || identifier == null)
			throw new IllegalArgumentException("Sound can not be null!");
		if (category == null)
			throw new IllegalAccessError("Category can not be null!");
		PacketOutSoundEffect packet = null;
		Chunk chunk = getChunk(loc, false);
		if (chunk == null)
			return;
		for (ChunkListener listener : chunk.getViewers()) {
			if (!(listener instanceof PlayerChunkListener))
				continue;
			PlayerChunkListener player = (PlayerChunkListener) listener;
			if (packet == null) {
				packet = new PacketOutSoundEffect();
				packet.setSound(sound);
				packet.setIdentifier(identifier);
				packet.setCategory(category);
				packet.setPitch(pitch);
				packet.setVolume(volume);
				packet.setLocation(loc.x, loc.y, loc.z);
				packet.setFixedRange(fixedRange);
				packet.setRange(range);
				packet.setSeed(seed);
			}
			player.getConnection().sendPacked(packet);
		}
	}
	
	@Override
	public void playSound(Entity entity, Sound sound, SoundCategory category, float volume, float pitch, long seed) {
		playSound(entity, sound, null, category, volume, pitch, seed, false, 0);
	}
	
	@Override
	public void playSound(Entity entity, String sound, SoundCategory category, float volume, float pitch, long seed, boolean fixedRange, float range) {
		playSound(entity, null, sound, category, volume, pitch, seed, fixedRange, range);
	}
	
	private void playSound(Entity entity, Sound sound, String identifier, SoundCategory category, float volume, float pitch, long seed, boolean fixedRange, float range) {
		if (entity == null)
			throw new IllegalArgumentException("Entity can not be null!");
		if (sound == null || identifier == null)
			throw new IllegalArgumentException("Sound can not be null!");
		if (category == null)
			throw new IllegalAccessError("Category can not be null!");
		PacketOutEntitySoundEffect packet = null;
		for (Player player : entity.getViewers()) {
			if (packet == null) {
				packet = new PacketOutEntitySoundEffect();
				packet.setSound(sound);
				packet.setIdentifier(identifier);
				packet.setFixedRange(fixedRange);
				packet.setRange(range);
				packet.setCategory(category);
				packet.setPitch(pitch);
				packet.setVolume(volume);
				packet.setEntityID(entity.getID());
				packet.setSeed(seed);
			}
			player.getConnection().sendPacked(packet);
		}
	}

	@Override
	public boolean hasFlag(WorldFlag flag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addFlag(WorldFlag flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFlag(WorldFlag flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Block getBlock(int x, int y, int z) {
		return chunks.getBlock(x, y, z);
	}

	@Override
	public BlockData getBlockData(int x, int y, int z) {
		return chunks.getBlockData(x, y ,z);
	}

	@Override
	public void tick() {
		age++;
		if (timeCycle) {
			if (time >= 24000) {
				time = 0;
			} else
			time++;
		}
		chunks.tick();
	}

	@Override
	public long getAge() {
		return age;
	}

	@Override
	public Entity getEntity(int entityID) {
		return chunks.getEntity(entityID);
	}

	@Override
	public Chunk getChunk(int x, int z) {
		return getChunk(x, z, true);
	}
	
	@Override
	public Chunk getChunk(int x, int z, boolean load) {
		return chunks.getChunk(x, z, load);
	}

	@Override
	public Chunk getChunk(SimpleLocation loc) {
		return getChunk(loc, true);
	}
	
	@Override
	public Chunk getChunk(SimpleLocation loc, boolean load) {
		return getChunk(loc.getBlockX() >> 4, loc.getBlockZ() >> 4, load);
	}

	@Override
	public boolean spawnEntity(Entity entity, double x, double y, double z, float pitch, float yaw) {
		if (!entity.asyncIsRemoved())
			return false;
		EntitySpawnEvent event = new EntitySpawnEvent(entity, this, x, y, z, pitch, yaw);
		HandlerList.callEvent(event);
		if (event.isCancelled())
			return false;
		entity.spawn(nextEntityID++, this, event.getX(), event.getY(), event.getZ(), event.getPitch(), event.getYaw());
		return true;
	}

	@Override
	public Entity spawnEntity(EntityType type, double x, double y, double z, float pitch, float yaw) {
		Entity ent = type.create(this);
		return spawnEntity(ent, x, y, z, pitch, yaw) ? ent : null;
	}

	@Override
	public int getEntityID() {
		return nextEntityID++;
	}
	
	@Override
	public int getEntityIDs(int count) {
		int next = nextEntityID+1;
		nextEntityID+= count;
		return next;
	}

	@Override
	public Dimension getDimension() {
		return dimension;
	}

}