package org.boot.service.privilege.impl;

import java.util.List;

import org.boot.facede.privilege.model.RoleButton;
import org.boot.facede.privilege.model.RoleButtonCriteria;
import org.boot.facede.privilege.model.RoleButtonCriteria.Criteria;
import org.boot.facede.privilege.service.RoleButtonService;
import org.boot.facede.privilege.util.UUIDUtil;
import org.boot.service.privilege.dao.base.RoleButtonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class RoleButtonServiceImpl implements RoleButtonService{
	
	@Autowired
	RoleButtonMapper roleButtonMapper;

	@Override
	@Transactional
	public void save(RoleButton roleButton) throws Exception {
		roleButton.setRbid(UUIDUtil.get32UUID());
		roleButtonMapper.insert(roleButton);
	}

	
	@Override
	public RoleButton findById(RoleButton roleButton) throws Exception {
		RoleButtonCriteria example = new RoleButtonCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andRoleidEqualTo(roleButton.getRoleid());
		criteria.andButtonidEqualTo(roleButton.getButtonid());
		List<RoleButton> lists = roleButtonMapper.selectByExample(example);
		if(lists.size()>0){
			return lists.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void delete(RoleButton roleButton) throws Exception {
		RoleButtonCriteria example = new RoleButtonCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andRoleidEqualTo(roleButton.getRoleid());
		criteria.andButtonidEqualTo(roleButton.getButtonid());
		roleButtonMapper.deleteByExample(example);
	}

	@Override
	public List<RoleButton> listAll() throws Exception {
		return roleButtonMapper.selectByExample(new RoleButtonCriteria());
	}

	@Override
	public List<String> listAllBrAndQxname(String roleid) throws Exception {
		
		return roleButtonMapper.listAllBrAndQxname(roleid);
	}

}
