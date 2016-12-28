package org.boot.facede.privilege.service;

import java.util.List;

import org.boot.facede.privilege.model.Role;


public interface RoleService {

	/**列出此组下级角色
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<Role> listAllRolesByPId(String roleId) throws Exception;
	
	/**通过id查找
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Role findObjectById(String roleId) throws Exception;
	
	/**添加
	 * @param pd
	 * @throws Exception
	 */
	public void add(Role role) throws Exception;
	
	/**保存修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(Role role) throws Exception;
	
	/**删除角色
	 * @param ROLE_ID
	 * @throws Exception
	 */
	public void deleteRoleById(String roleid) throws Exception;
	
	/**给当前角色附加菜单权限
	 * @param role
	 * @throws Exception
	 */
	public void updateRoleRights(Role role) throws Exception;
	
	/**通过id查找
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public Role getRoleById(String roleid) throws Exception;
	
	/**给全部子角色加菜单权限
	 * @param pd
	 * @throws Exception
	 */
	public void setAllRights(String rights ,String roleid) throws Exception;
	
	/**权限(增删改查)
	 * @param msg 区分增删改查
	 * @param pd
	 * @throws Exception
	 */
	public void saveB4Button(Role role) throws Exception;
}
