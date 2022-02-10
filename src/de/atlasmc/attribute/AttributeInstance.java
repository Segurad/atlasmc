package de.atlasmc.attribute;

import java.util.ArrayList;
import java.util.List;

public class AttributeInstance {

	private final Attribute attribute;
	private final double defaultValue;
	private double baseValue;
	private List<AttributeModifier> modifiers;
	
	public AttributeInstance(Attribute attribute, double defaultValue) {
		this.attribute = attribute;
		this.defaultValue = defaultValue;
		baseValue = defaultValue;
	}
	
	public Attribute getAttribute() {
		return attribute;
	}

	public double getDefaultValue() {
		return defaultValue;
	}

	public int getModifierCount() {
		return modifiers != null ? modifiers.size() : 0;
	}

	public List<AttributeModifier> getModifiers() {
		if (modifiers == null) modifiers = new ArrayList<AttributeModifier>();
		return modifiers;
	}
	
	public double getBaseValue() {
		return baseValue;
	}
	
	public void setBaseValue(double value) {
		this.baseValue = value;
	}
	
	public void addAttributeModififer(AttributeModifier modifier) {
		getModifiers().add(modifier);
	}

	public boolean removeModifiers() {
		if (modifiers == null || modifiers.isEmpty())
			return false;
		modifiers.clear();
		return true;
	}

	public boolean hasModifiers() {
		return modifiers != null && !modifiers.isEmpty();
	}

	public void setModifiers(List<AttributeModifier> modifiers) {
		if (modifiers == null)
			return;
		List<AttributeModifier> mods = getModifiers();
		mods.clear();
		mods.addAll(modifiers);
	}

}
