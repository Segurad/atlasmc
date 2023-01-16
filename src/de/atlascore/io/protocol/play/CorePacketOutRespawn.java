package de.atlascore.io.protocol.play;

import java.io.IOException;

import de.atlascore.io.ConnectionHandler;
import de.atlasmc.Gamemode;
import static de.atlasmc.io.AbstractPacket.*;

import de.atlasmc.io.PacketIO;
import de.atlasmc.io.protocol.play.PacketOutRespawn;
import de.atlasmc.util.nbt.io.NBTNIOReader;
import de.atlasmc.util.nbt.io.NBTNIOWriter;
import io.netty.buffer.ByteBuf;

public class CorePacketOutRespawn extends PacketIO<PacketOutRespawn> {

	@Override
	public void read(PacketOutRespawn packet, ByteBuf in, ConnectionHandler handler) throws IOException {
		// TODO reader dimension
		NBTNIOReader reader = new NBTNIOReader(in);
		reader.readNextEntry();
		reader.skipToEnd();
		reader.close();
		packet.setWorld(readString(in));
		packet.setSeed(in.readLong());
		packet.setGamemode(Gamemode.getByID(in.readUnsignedByte()));
		packet.setGamemode(Gamemode.getByID(in.readUnsignedByte()));
		packet.setDebug(in.readBoolean());
		packet.setFlat(in.readBoolean());
		packet.setCopymeta(in.readBoolean());
	}

	@Override
	public void write(PacketOutRespawn packet, ByteBuf out, ConnectionHandler handler) throws IOException {
		NBTNIOWriter writer = new NBTNIOWriter(out);
		writer.writeCompoundTag();
		packet.getDimension().toNBT(writer, false);
		writer.writeEndTag();
		writer.close();
		writeString(packet.getWorld(), out);
		out.writeLong(packet.getSeed());
		out.writeByte(packet.getGamemode().getID());
		out.writeByte(packet.getPreviousGamemode().getID());
		out.writeBoolean(packet.isDebug());
		out.writeBoolean(packet.isFlat());
		out.writeBoolean(packet.isCopymeta());
	}
	
	@Override
	public PacketOutRespawn createPacketData() {
		return new PacketOutRespawn();
	}

}
