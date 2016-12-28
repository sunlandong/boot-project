package org.boot.facede.privilege.service;

import java.util.List;

import org.boot.facede.privilege.model.Menu;
import org.boot.facede.privilege.model.MenuCustom;

public interface MenuService {

	/**
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<Menu> listSubMenuByParentId(String parentId)throws Exception;
	
	/**
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Menu getMenuById(long menuid) throws Exception;
	
	/**
	 * @param menu
	 * @throws Exception
	 */
	public void saveMenu(Menu menu) throws Exception;
	
	
	/**
	 * @param MENU_ID
	 * @throws Exception
	 */
	public void deleteMenuById(long menuid) throws Exception;
	
	/**
	 * @param menu
	 * @throws Exception
	 */
	public void edit(Menu menu) throws Exception;
	
	/**
	 * @return
	 * @throws Exception
	 */
	public int editicon(Menu menu) throws Exception;
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List<MenuCustom> listAllMenu(String menuid) throws Exception;
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List<MenuCustom> listAllMenuQx(String menuid) throws Exception;
}
