package org.boot.facede.privilege.service;

import java.util.List;

import org.boot.facede.privilege.model.RoleButton;

public interface RoleButtonService {

	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(RoleButton roleButton)throws Exception;
	
	/**通过(角色ID和按钮ID)获取数据
	 * @param pd
	 * @throws Exception
	 */
	public RoleButton findById(RoleButton roleButton)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(RoleButton roleButton)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<RoleButton> listAll()throws Exception;
	
	/**列表(全部)左连接按钮表,查出安全权限标识
	 * @param pd
	 * @throws Exception
	 */
	public List<String> listAllBrAndQxname(String roleid)throws Exception;
	
}
