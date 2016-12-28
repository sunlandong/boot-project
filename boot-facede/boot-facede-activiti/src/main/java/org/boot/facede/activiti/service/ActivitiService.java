package org.boot.facede.activiti.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.boot.facede.activiti.model.Application;
import org.boot.facede.activiti.model.ApproveInfo;
import org.boot.facede.activiti.model.TaskView;
import org.boot.facede.activiti.model.Template;

public interface ActivitiService {
	

	/**
	 * @param file  在action层文件上传的内容
	 * @param processName  流程名称  
	 */ 
	public void deploy(File file,String processName) throws Exception;
	
	/**
	 * 查询所有的部署信息
	 */
	public List<Map<String,Object>> getAllLastDeployment() throws Exception;
	
	/**
	 * 查询所有的流程定义的信息
	 */
	public List<ProcessDefinition> getAllProcessDefinition() throws Exception;
	
	/**
	 * 删除某一个部署
	 */
	public void deleteDeploymentByPDKey(String pdkey) throws Exception;
	
	/**
	 * 查看流程图
	 */
	public InputStream showImages(String pdid) throws Exception;
	
	/**
	 * 启动流程实例
	 *   1、启动流程实例的api
	 *   2、传入一个参数：新增加的请假单的id
	 *   3、因为在提交申请的任务中有#{userID},所以在进入提交申请的任务之前，必须通过流程变量给userID赋值
	 */
	public void startPI(Long id,String userId) throws Exception;
	
	/**
	 * 提交申请
	 */
	public void submitApplication(Application application) throws Exception;
	
	/**
	 * 根据用户ID查询用户的申请列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Application> findApplicationByUserId(String userId) throws Exception;
	
	/**
	 * 当前登录人登录系统以后要执行的任务
	 */
	public List<Task> getTasksByAssignee(String userId) throws Exception;
	
	/**
	 * 根据taskId得到当前任务所在的流程实例正在执行的节点的所有的sequenceFlow的名称
	 * @param taskId
	 * @return
	 */
	public List<PvmTransition> getPvmTransitions(String taskId) throws Exception;
	/**
	 * 根据taskId得到当前流程实例正在执行的节点ActivityImpl
	 */
	public ActivityImpl getActivityImplByTaskId(String taskId) throws Exception;
	
	/**
	 * 根据taskId查找businessKey
	 */
	public String getBusinessKeyByTaskId(String taskId) throws Exception;
	
	/**
	 * 根据taskId完成任务，并且在完成任务以后判断流程实例是否结束
	 */
	public ProcessInstance finishTask(String taskId) throws Exception;
	
	/**
	 * 增加模板
	 */
	public void saveTemplate(Template template) throws Exception;
	
	/**
	 * 删除模板根据模板ID
	 */
	public String deleteTemplate(String templateId) throws Exception;
	
	/**
	 * 修改模板
	 */
	public void updateTemplate(Template template)throws Exception;
	
	/**
	 * 查询所有模板
	 */
	public List<Template> findAllTemplate() throws Exception;
	
	/**
	 * 根据模板ID查询模板
	 * @param templateId
	 * @return
	 * @throws Exception
	 */
	public Template findAllTemplateById(String templateId) throws Exception;
	
	/**
	 * 根据申请ID查询审批信息
	 * @param applicationId
	 * @return
	 * @throws Exception
	 */
	public List<ApproveInfo> findApproveInfoByApplicationId(String applicationId) throws Exception;
	/**
	 * 用户查询待办任务
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<TaskView> findTaskViewByUserId(String userId) throws Exception;
	/**
	 * 审批处理
	 */
	public void approve(ApproveInfo approveInfo, String taskId) throws Exception;
}
