package org.boot.service.core.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.boot.common.core.utils.JSONUtils;
import org.boot.common.core.utils.StringUtil;
import org.boot.common.core.utils.SystemCons;
import org.boot.facede.core.model.Org;
import org.boot.facede.core.model.OrgCriteria;
import org.boot.facede.core.model.OrgCriteria.Criteria;
import org.boot.facede.core.service.OrgService;
import org.boot.service.core.dao.base.OrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class OrgServiceImpl implements OrgService{
	
	@Autowired
	OrgMapper orgMapper;
	
	@Override
	public List<Org> getOrgListByParentId(String parentId) throws Exception {
		// TODO Auto-generated method stub
		OrgCriteria example = new OrgCriteria();
		example.setOrderByClause(" inSortNo ");
		Criteria criteria = example.createCriteria();
		criteria.andVcParentIdEqualTo(parentId);
		List<Org> selectByExample = orgMapper.selectByExample(example);
		
		return selectByExample;
	}

	@Override
	public JSONArray getOrgTreeData(String orgId) throws Exception {
		//这里是递归查询数据，在考虑是否后期是全都查询出来，在内存中递归处理
		JSONArray result = new JSONArray();
		List<Org> parentList = this.getOrgListByParentId(orgId);
		for(Org org : parentList) {
			JSONObject objectToJson = JSONUtils.objectToJson(org);
			objectToJson.put("children", this.getOrgTreeData(org.getVcId()));
			result.add(objectToJson);
		}
		return result;
	}

	@Override
	public List<Org> getOrgListWithPage(String start, String limit,
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void saveOrg(Org org) throws Exception {
		org.setVcId(StringUtil.get36UUID());
		org.setVcIsLeaf(SystemCons.IS.YES);
		org.setDtCreeteTime(new Date());
		org.setVcIsDel(SystemCons.IS.NO);
		orgMapper.insert(org);
		//更新父子节点是否是叶子节点
		Org parentOrg = new Org();
		parentOrg.setVcId(org.getVcParentId());
		parentOrg.setVcIsLeaf(SystemCons.IS.NO);
		orgMapper.updateByPrimaryKeySelective(parentOrg);
		
	}

	@Override
	@Transactional
	public void updateOrg(Org org) throws Exception {
		orgMapper.updateByPrimaryKeySelective(org);
	}

	@Override
	@Transactional
	public void deleteOrg(Org org) throws Exception {
		//如果删除师父节点，且有子节点，则不能删除
		//当子节点删除完之后，父节点更新叶子节点
		org.setVcIsDel(SystemCons.IS.YES);
		org.setDtCreeteTime(new Date());
		orgMapper.updateByPrimaryKeySelective(org);
		
		List<Org> orgListByParentId = this.getOrgListByParentId(org.getVcParentId());
		if(orgListByParentId.size() == 0) {
			//此节点下面没有节点，更新父节点
			Org parentOrg = new Org();
			parentOrg.setVcId(org.getVcParentId());
			parentOrg.setVcIsLeaf(SystemCons.IS.YES);
			parentOrg.setVcIsAutoExpand(SystemCons.IS.NO);
			orgMapper.updateByPrimaryKeySelective(parentOrg);
		}
		//删除该部门下的用户
	}
	
	


}
