package de.atlasmc.util.nbt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.atlasmc.util.Validate;

public final class ListTag<T extends NBT> extends AbstractTag implements Iterable<NBT> {

	private List<T> data;
	private TagType datatype;
	
	public ListTag(String name, TagType datatype) {
		data = new ArrayList<>();
		this.datatype = datatype;
		this.name = name;
	}
	
	public ListTag() {}

	@Override
	public List<T> getData() {
		return data;
	}
	
	public void addTag(T tag) {
		Validate.notNull(tag, "NBT can not be null!");
		Validate.isTrue(datatype == tag.getType(), "Illegal TagType:" + tag.getType().name() + " " + datatype.name() + " expected!");
		data.add(tag);
	}
	
	@SuppressWarnings("unchecked")
	public T createEntry(Object field) {
		T tag = (T) datatype.createTag(field);
		data.add(tag);
		return tag;
	}
	
	public TagType getDataType() {
		return datatype;
	}

	@Override
	public TagType getType() {
		return TagType.LIST;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setData(Object data) {
		NBT tag = (NBT) data;
		Validate.isTrue(datatype == tag.getType(), "Illegal TagType:" + tag.getType().name() + " " + datatype.name() + " expected!");
		addTag((T) tag);
	}

	public int size() {
		return data.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<NBT> iterator() {
		return (Iterator<NBT>) data.iterator();
	}

}
