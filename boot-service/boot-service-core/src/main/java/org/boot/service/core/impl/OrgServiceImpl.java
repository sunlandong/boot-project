package org.boot.service.core.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.boot.common.core.exception.BizException;
import org.boot.common.core.utils.JSONUtils;
import org.boot.common.core.utils.StringUtil;
import org.boot.common.core.utils.SystemCons;
import org.boot.facede.core.model.Org;
import org.boot.facede.core.model.OrgCriteria;
import org.boot.facede.core.model.OrgCriteria.Criteria;
import org.boot.facede.core.model.User;
import org.boot.facede.core.model.UserCriteria;
import org.boot.facede.core.service.OrgService;
import org.boot.service.core.dao.base.OrgMapper;
import org.boot.service.core.dao.base.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class OrgServiceImpl implements OrgService{
	
	@Autowired
	OrgMapper orgMapper;
	@Autowired
	UserMapper userMapper;
	
	@Override
	public List<Org> getOrgListByParentId(String parentId) throws Exception {
		// TODO Auto-generated method stub
		OrgCriteria example = new OrgCriteria();
		example.setOrderByClause(" inSortNo ");
		Criteria criteria = example.createCriteria();
		criteria.andVcParentIdEqualTo(parentId);
		criteria.andVcIsDelEqualTo(SystemCons.IS.NO);
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
	public List<Org> getOrgListWithPage(int start, int limit,
			Map<String, Object> param) throws Exception {
		OrgCriteria example = new OrgCriteria();
		example.createCriteria().andVcIsDelEqualTo(SystemCons.IS.NO);
		return orgMapper.selectByExample(example);
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
	public void deleteOrg(String id) throws Exception {
		Org org = this.getOrg(id);
		//如果删除师父节点，且有子节点，则不能删除
		List<Org> orgListByParentId = this.getOrgListByParentId(org.getVcId());
		//当子节点删除完之后，父节点更新叶子节点
		org.setVcIsDel(SystemCons.IS.YES);
		org.setDtCreeteTime(new Date());
		orgMapper.updateByPrimaryKeySelective(org);
		
		if(orgListByParentId.size() == 0) {
			//此节点下面没有节点，更新父节点
			Org parentOrg = new Org();
			parentOrg.setVcId(org.getVcParentId());
			parentOrg.setVcIsLeaf(SystemCons.IS.YES);
			parentOrg.setVcIsAutoExpand(SystemCons.IS.NO);
			orgMapper.updateByPrimaryKeySelective(parentOrg);
		} else {
			throw new BizException("该节点下有子节点,请先删除子节点");
		}
		//删除该部门下的用户
		UserCriteria example = new UserCriteria();
		org.boot.facede.core.model.UserCriteria.Criteria userCriteria = example.createCriteria();
		userCriteria.andVcOrgIdEqualTo(org.getVcId());
		User user = new User();
		user.setVcIsDel(SystemCons.IS.YES);
		userMapper.updateByExampleSelective(user, example);
		
	}

	@Override
	public Org getOrg(String orgid) throws Exception {
		OrgCriteria example= new OrgCriteria();
		example.createCriteria().andVcIsDelEqualTo(SystemCons.IS.NO).andVcIdEqualTo(orgid);
		List<Org> selectByExample = orgMapper.selectByExample(example);
		if(selectByExample.size()==0) {
			throw new BizException("没有结果");
		}
		return selectByExample.get(0);
	}
	
	


}
