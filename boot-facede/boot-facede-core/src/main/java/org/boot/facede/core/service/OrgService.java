package org.boot.facede.core.service;

import java.util.List;
import java.util.Map;

import org.boot.facede.core.model.Org;

import com.alibaba.fastjson.JSONArray;

public interface OrgService {

	List<Org> getOrgListByParentId(String parentId) throws Exception;
	
	JSONArray getOrgTreeData(String orgId) throws Exception;
	
	List<Org> getOrgListWithPage(int start, int limit, Map<String, Object> param)throws Exception;
	
	void saveOrg(Org org) throws Exception;
	
	void updateOrg(Org org)throws Exception;
	
	void deleteOrg(String id) throws Exception;
	
	Org getOrg(String orgid)throws Exception;
}
