package de.atlasmc.inventory.component;

import java.util.List;

import de.atlasmc.NamespacedKey;
import it.unimi.dsi.fastutil.booleans.BooleanList;
import it.unimi.dsi.fastutil.floats.FloatList;
import it.unimi.dsi.fastutil.ints.IntList;

public interface CustomModelDataComponent extends ItemComponent {
	
	public static final NamespacedKey COMPONENT_KEY = NamespacedKey.literal("minecraft:custom_model_data");
	
	FloatList getFloats();
	
	void setFloats(FloatList floats);
	
	boolean hasFloats();
	
	BooleanList getFlags();
	
	void setFlags(BooleanList flags);
	
	boolean hasFlags();
	
	List<String> getStrings();
	
	void setStrings(List<String> strings);
	
	boolean hasStrings();
	
	IntList getColors();
	
	void setColors(IntList colors);
	
	boolean hasColors();
	
	CustomModelDataComponent clone();

}
