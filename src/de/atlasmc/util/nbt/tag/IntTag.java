package de.atlasmc.util.nbt.tag;

import java.io.IOException;

import de.atlasmc.util.nbt.NBTException;
import de.atlasmc.util.nbt.TagType;
import de.atlasmc.util.nbt.io.NBTReader;
import de.atlasmc.util.nbt.io.NBTWriter;

public final class IntTag extends AbstractTag {

	private int data;
	
	public IntTag(String name, int data) {
		this.name = name;
		this.data = data;
	}

	public IntTag() {}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public TagType getType() {
		return TagType.INT;
	}

	@Override
	public void setData(Object data) {
		this.data = (int) data;
	}
	
	@Override
	public IntTag clone() {
		return (IntTag) super.clone();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		return data == ((IntTag) obj).data;
	}

	@Override
	public void toNBT(NBTWriter writer, boolean systemData) throws IOException {
		writer.writeIntTag(name, data);
	}

	@Override
	public void fromNBT(NBTReader reader) throws IOException {
		if (reader.getType() != TagType.INT)
			throw new NBTException("Can not read " + reader.getType().name() + " as INT");
		CharSequence name = reader.getFieldName();
		if (name != null)
			this.name = name.toString();
		data = reader.readIntTag();
	}

}
