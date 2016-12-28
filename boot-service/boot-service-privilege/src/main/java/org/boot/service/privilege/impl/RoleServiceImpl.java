package org.boot.service.privilege.impl;

import java.util.List;

import org.boot.facede.privilege.model.Role;
import org.boot.facede.privilege.model.RoleCriteria;
import org.boot.facede.privilege.model.RoleCriteria.Criteria;
import org.boot.facede.privilege.service.RoleService;
import org.boot.facede.privilege.util.UUIDUtil;
import org.boot.service.privilege.dao.base.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleMapper roleMapper;

	@Override
	public List<Role> listAllRolesByPId(String roleId) throws Exception {
		RoleCriteria example = new RoleCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(roleId);
		List<Role> roles = roleMapper.selectByExample(example);
		return roles;
	}

	@Override
	public Role findObjectById(String roleId) throws Exception {
		
		return roleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	@Transactional
	public void add(Role role) throws Exception {
		role.setRoleid(UUIDUtil.get32UUID());
		roleMapper.insert(role);
	}

	@Override
	@Transactional
	public void edit(Role role) throws Exception {
		Role edit = new Role();
		edit.setRoleid(role.getRoleid());
		edit.setRolename(role.getRolename());
		roleMapper.updateByPrimaryKeySelective(edit);
	}

	@Override
	@Transactional
	public void deleteRoleById(String roleid) throws Exception {
		roleMapper.deleteByPrimaryKey(roleid);
	}

	@Override
	@Transactional
	public void updateRoleRights(Role role) throws Exception {
		Role update = new Role();
		update.setRoleid(role.getRoleid());
		update.setRights(role.getRights());
		roleMapper.updateByPrimaryKeySelective(update);
	}

	@Override
	public Role getRoleById(String roleid) throws Exception {
		return roleMapper.selectByPrimaryKey(roleid);
	}

	
	/**
	 * 给全部子角色加菜单权限
	 * @param pd
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void setAllRights(String rights ,String roleid) throws Exception {
		RoleCriteria example = new RoleCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(roleid);
		
		Role right = new Role();
		right.setRights(rights);
		
		roleMapper.updateByExampleSelective(right, example);
	}

	/**权限(增删改查)
	 * 该接口所传role，必须是new的，除主键和需要更新的权限字段以外，其他字段为空
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void saveB4Button(Role role) throws Exception {
		// TODO Auto-generated method stub
		roleMapper.updateByPrimaryKeySelective(role);
	}

}
