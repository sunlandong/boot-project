package org.boot.facede.privilege.model;

import java.util.List;

public class MenuCustom extends Menu{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1506794172088214178L;
	
	private String target;
	private Menu parentMenu;
	private List<MenuCustom> subMenu;
	private boolean hasMenu = false;
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Menu getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
	
	public List<MenuCustom> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<MenuCustom> subMenu) {
		this.subMenu = subMenu;
	}
	public boolean isHasMenu() {
		return hasMenu;
	}
	public void setHasMenu(boolean hasMenu) {
		this.hasMenu = hasMenu;
	}

}
