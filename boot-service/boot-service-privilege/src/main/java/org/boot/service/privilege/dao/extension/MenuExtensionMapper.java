package org.boot.service.privilege.dao.extension;

import java.util.List;

import org.boot.facede.privilege.model.Menu;

public interface MenuExtensionMapper {
	
	long getMaxId()throws Exception;

	List<Menu> listAllMenu(long menuid) throws Exception;
}
