package de.atlasmc.block.data;

public interface Chest extends Directional, Waterlogged {
	
	public Type getType();
	public void setType(Type type);
	
	public static enum Type {
		SINGLE,
		LEFT,
		RIGHT
	}

}
