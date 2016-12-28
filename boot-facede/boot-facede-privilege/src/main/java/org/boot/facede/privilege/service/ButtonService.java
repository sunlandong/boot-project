package org.boot.facede.privilege.service;

import java.util.List;
import java.util.Map;

import org.boot.facede.privilege.model.Button;



public interface ButtonService {

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(Button button)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(String buttonid)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(Button button)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<Button> listWithSearch(Map<String, Object> params)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<Button> listAll()throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public Button findById(String buttonid)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(List<String> buttonids)throws Exception;
}
