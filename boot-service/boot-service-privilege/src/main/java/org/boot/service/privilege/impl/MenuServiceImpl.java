package org.boot.service.privilege.impl;

import java.util.ArrayList;
import java.util.List;

import org.boot.facede.privilege.model.Menu;
import org.boot.facede.privilege.model.MenuCriteria;
import org.boot.facede.privilege.model.MenuCriteria.Criteria;
import org.boot.facede.privilege.model.MenuCustom;
import org.boot.facede.privilege.service.MenuService;
import org.boot.service.privilege.dao.base.MenuMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class MenuServiceImpl implements MenuService{
	
	@Autowired
	MenuMapper menuMapper;

	@Override
	public List<Menu> listSubMenuByParentId(String parentId) throws Exception {
		MenuCriteria example = new MenuCriteria();
		example.setOrderByClause(" menuorder ");
		Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(parentId);
		
		List<Menu> menus = menuMapper.selectByExample(example);
		return menus;
	}

	@Override
	public Menu getMenuById(long menuid) throws Exception {
		return menuMapper.selectByPrimaryKey(menuid);
	}

	@Override
	@Transactional
	public void saveMenu(Menu menu) throws Exception {
		
		menu.setMenuid(menuMapper.getMaxId());
		menuMapper.insert(menu);
	}


	@Override
	@Transactional
	public void deleteMenuById(long menuid) throws Exception {
		menuMapper.deleteByPrimaryKey(menuid);
	}

	@Override
	@Transactional
	public void edit(Menu menu) throws Exception {
		menuMapper.updateByPrimaryKeySelective(menu);
	}

	@Override
	@Transactional
	public int editicon(Menu menu) throws Exception {
		Menu editicon = new Menu();
		editicon.setMenuid(menu.getMenuid());
		editicon.setMenuicon(menu.getMenuicon());
		return menuMapper.updateByPrimaryKeySelective(editicon);
	}

	/**
	 * 菜单管理页面用的，所以url需要重新设置
	 */
	@Override
	public List<MenuCustom> listAllMenu(String menuid) throws Exception {
		List<MenuCustom> result = new ArrayList<MenuCustom>();
		List<Menu> menuList = this.listSubMenuByParentId(menuid);
		for(Menu menu : menuList){
			MenuCustom menuCustom = new MenuCustom();
			BeanUtils.copyProperties(menu, menuCustom);
			menuCustom.setSubMenu(this.listAllMenu(menu.getMenuid()+""));
			menuCustom.setTarget("treeFrame");
			result.add(menuCustom);
		}
		return result;
	}

	@Override
	public List<MenuCustom> listAllMenuQx(String menuid) throws Exception {
		List<MenuCustom> result = new ArrayList<MenuCustom>();
		List<Menu> menuList = this.listSubMenuByParentId(menuid);
		for(Menu menu : menuList){
			MenuCustom menuCustom = new MenuCustom();
			BeanUtils.copyProperties(menu, menuCustom);
			menuCustom.setSubMenu(this.listAllMenu(menu.getMenuid()+""));
			menuCustom.setTarget("treeFrame");
			result.add(menuCustom);
		}
		return result;
	}

}
