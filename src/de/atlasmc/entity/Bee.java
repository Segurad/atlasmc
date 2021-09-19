package de.atlasmc.entity;

public interface Bee extends Animal {

	public boolean isAngry();
	public boolean hasStung();
	public boolean hasNectar();
	public int getAnger();
	public int getTicksInHive();
	public int getHiveMinOccupationTicks();
	
}
