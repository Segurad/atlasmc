package de.atlasmc.permission;

public interface PermissionGroup extends PermissionGroupHolder, PermissionHolder, PermissionContextHolder {
	
	public int getPower();

}
