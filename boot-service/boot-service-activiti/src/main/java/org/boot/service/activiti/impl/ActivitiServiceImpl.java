package org.boot.service.activiti.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.boot.facede.activiti.model.Application;
import org.boot.facede.activiti.model.ApplicationCriteria;
import org.boot.facede.activiti.model.ApproveInfo;
import org.boot.facede.activiti.model.ApproveInfoCriteria;
import org.boot.facede.activiti.model.Template;
import org.boot.facede.activiti.service.ActivitiService;
import org.boot.service.activiti.dao.base.ApplicationMapper;
import org.boot.service.activiti.dao.base.ApproveInfoMapper;
import org.boot.service.activiti.dao.base.TemplateMapper;
import org.boot.service.activiti.utils.ApproveStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivitiServiceImpl implements ActivitiService {
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TemplateMapper templateMapper;
	@Autowired
	private ApplicationMapper applicationMapper;
	@Autowired
	private ApproveInfoMapper approveInfoMapper;
	
	/**
	 * @param file  在action层文件上传的内容  
	 * @param processName  流程名称  
	 */ 
	public void deploy(File file,String processName) throws Exception{
		InputStream in = new FileInputStream(file);
		ZipInputStream zipInputStream = new ZipInputStream(in);
		repositoryService
		.createDeployment()
		.name(processName)
		.addZipInputStream(zipInputStream)
		.deploy();
	} 
	
	/**
	 * 查询最新版本的流程定义列表  展示列表使用这一个
	 */
	public List<Map<String,Object>> getAllLastDeployment(){
		// 按照版本升序排序
		List<ProcessDefinition> processDefinitions = 
		repositoryService.createProcessDefinitionQuery()
		.orderByProcessDefinitionVersion()
		.asc()
		.list();
		Map<String, ProcessDefinitionEntity> map = new HashMap<String, ProcessDefinitionEntity>();
		for (ProcessDefinition pd : processDefinitions) {
			map.put(pd.getKey(), (ProcessDefinitionEntity) pd);
		}
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		Map<String, Object> tmpMap;
		for (ProcessDefinition processDefinition : map.values()) {
			tmpMap = new HashMap<String , Object>();
			tmpMap.put("name", processDefinition.getName());
			tmpMap.put("version", processDefinition.getVersion());
			tmpMap.put("key", processDefinition.getKey());
			tmpMap.put("id", processDefinition.getId());
			maps.add(tmpMap);
		}
		return maps;
	}
	
	/**
	 * 查询所有的流程定义的信息
	 */
	public List<ProcessDefinition> getAllProcessDefinition(){
		return repositoryService
				.createProcessDefinitionQuery()
				.orderByProcessDefinitionVersion()
				.desc()
				.list();
	}
	
	/**
	 * 删除某一个部署  0
	 */
	public void deleteDeploymentByPDKey(String pdkey){
		List<ProcessDefinition> processDefinitions = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey(pdkey)
				.list();
		for (ProcessDefinition pd : processDefinitions) {
			String deploymentId = pd.getDeploymentId();
			repositoryService.deleteDeployment(deploymentId, true);
		}
	}
	
	/**
	 * 查看流程图   0
	 */
	public InputStream showImages(String pdid){
		return repositoryService
		.getProcessDiagram(pdid);
	}
	
	/**
	 * 启动流程实例
	 *   1、启动流程实例的api
	 *   2、传入一个参数：新增加的请假单的id
	 *   3、因为在提交申请的任务中有#{userID},所以在进入提交申请的任务之前，必须通过流程变量给userID赋值
	 */
	public void startPI(Long id,String userId){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userID", userId);
		runtimeService
		/**
		 * 第二个参数是businesskey:请假单的主键
		 */
		.startProcessInstanceByKey("LeaveBill", ""+id, variables);
	}
	
	/**
	 * 已完成接口
	 */
	@Override
	public void submitApplication(Application application) throws Exception {
		application.setVcStatus(ApproveStatus.STATUS_APPROVING);
		//application.setVcName("测试申请名称");
		application.setDtCtime(new Date());
		applicationMapper.insertSelective(application);
		Template template = templateMapper.selectByPrimaryKey(application.getVcTemplateId());
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("application", application);
		variables.put("userId", application.getVcUserId());
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(template.getVcPdkey(), application.getVcId(), variables);
		//提交后办理第一个任务
		Task task = taskService.createTaskQuery()
		//.taskAssignee(application.getVcUserId())
		.processInstanceId(pi.getId())
		.singleResult();
		taskService.complete(task.getId());
	}
	
	
	/**
	 * 当前登录人登录系统以后要执行的任务
	 */
	public List<Task> getTasksByAssignee(String userId){
		return taskService
		.createTaskQuery()
		.taskAssignee(userId)
		.orderByTaskCreateTime()
		.desc()
		.list();
	}
	
	/**
	 * 根据taskId得到当前任务所在的流程实例正在执行的节点的所有的sequenceFlow的名称
	 * @param taskId
	 * @return
	 */
	public List<PvmTransition> getPvmTransitions(String taskId){
		ActivityImpl activityImpl = this.getActivityImplByTaskId(taskId);
		return activityImpl.getOutgoingTransitions();
	}
	
	/**
	 * 根据taskId得到当前流程实例正在执行的节点ActivityImpl
	 */
	public ActivityImpl getActivityImplByTaskId(String taskId){
		/**
		 * 根据taskId获取到task
		 */
		Task task = taskService
		.createTaskQuery()
		.taskId(taskId)
		.singleResult(); 
		/**
		 * 根据task获取到pi
		 */
		ProcessInstance pi = runtimeService
		.createProcessInstanceQuery()
		.processInstanceId(task.getProcessInstanceId())
		.singleResult();
		
		ProcessDefinitionEntity processDefinitionEntity = this.getProcessDefinitionEntityByTaskId(taskId);
		return processDefinitionEntity.findActivity(pi.getActivityId());
	}
	
	/**
	 * 根据taskId获取到ProcessDefinitionEntity
	 */
	private ProcessDefinitionEntity getProcessDefinitionEntityByTaskId(String taskId){
		Task task = taskService
		.createTaskQuery()
		.taskId(taskId)
		.singleResult();
		return (ProcessDefinitionEntity)repositoryService
		.getProcessDefinition(task.getProcessDefinitionId());
	}
	
	/**
	 * 根据taskId查找businessKey
	 */
	public String getBusinessKeyByTaskId(String taskId){
		Task task = taskService
				.createTaskQuery()
				.taskId(taskId)
				.singleResult();
		ProcessInstance pi = runtimeService
		.createProcessInstanceQuery()
		.processInstanceId(task.getProcessInstanceId())
		.singleResult();
		return pi.getBusinessKey();
	}
	
	/**
	 * 根据taskId完成任务，并且在完成任务以后判断流程实例是否结束
	 */
	public ProcessInstance finishTask(String taskId){
		/**
		 * 根据taskId提取任务
		 */
		Task task = taskService
		.createTaskQuery()
		.taskId(taskId)
		.singleResult();
		//根据任务得到piid
		String piid = task.getProcessInstanceId();
		taskService
		.complete(taskId);
		//根据piid过滤流程实例
		ProcessInstance pi = runtimeService
		.createProcessInstanceQuery()
		.processInstanceId(piid)
		.singleResult();
		return pi;//如果整个流程实例结束了，则pi为null,如果没有结束就是一个对象
	}

	@Override
	public void saveTemplate(Template template) throws Exception {
		templateMapper.insertSelective(template);
	}

	@Override
	public String deleteTemplate(String templateId) throws Exception {
		Template template = templateMapper.selectByPrimaryKey(templateId);
		templateMapper.deleteByPrimaryKey(templateId);
		return template.getVcFilePath();
	}

	@Override
	public void updateTemplate(Template template) throws Exception {
		templateMapper.updateByPrimaryKeySelective(template);
	}

	@Override
	public List<Template> findAllTemplate() throws Exception {
		return templateMapper.selectByExample(null);
	}
	@Override
	public Template findAllTemplateById(String templateId) throws Exception {
		return templateMapper.selectByPrimaryKey(templateId);
	}

	@Override
	public List<Application> findApplicationByUserId(String userId)
			throws Exception {
		ApplicationCriteria applicationCriteria = new ApplicationCriteria();
		applicationCriteria.setOrderByClause("DT_CTIME_ DESC");
		applicationCriteria.createCriteria().andVcUserIdEqualTo(userId);
		return applicationMapper.selectByExample(applicationCriteria);
	}

	@Override
	public List<ApproveInfo> findApproveInfoByApplicationId(String applicationId)
			throws Exception {
		ApproveInfoCriteria approveInfoCriteria = new ApproveInfoCriteria();
		approveInfoCriteria.createCriteria().andVcApplicationIdEqualTo(applicationId);
		approveInfoCriteria.setOrderByClause("DT_CTIME_ DESC");
		List<ApproveInfo> approveInfos = approveInfoMapper.selectByExampleWithBLOBs(approveInfoCriteria);
		return approveInfos;
	}

	@Override
	public List<Map<String,Object>> findTaskViewByUserId(String userId) throws Exception {
		List<Task> tasks = taskService
		.createTaskQuery()
		.taskAssignee(userId)
		.orderByTaskCreateTime()
		.desc()
		.list();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> obj = new HashMap<String,Object>();
		for (Task task : tasks) {
			obj.put("taskId", task.getId());
			Application application = (Application) taskService
					.getVariable(task.getId(), "application");
			obj.put("application", application);
			list.add(obj);
		}
		return list;
	}
	
	
	/**
	 * 审批处理
	 */
	public void approve(ApproveInfo approveInfo, String taskId) {
		Task task = taskService
				.createTaskQuery()
				.taskId(taskId)
				.singleResult();
		// 获得流程实例id
		String processInstanceId = task.getProcessInstanceId();
		// 保存审批信息实体
		approveInfoMapper.insertSelective(approveInfo);
		// 办理当前的任务
		taskService.complete(taskId);

		// 查询当前的流程实例是否存在
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
//		Application application = approveInfo.getApplication();// 持久对象

//		if (ai.getApproval()) {// 3、如果审批的结果为“通过”
//			if (processInstance == null) {// 如果当前办理人为最后一个节点，将申请的状态修改为“已通过”
//				application.setStatus(Application.STATUS_APPROVED);
//			}
//		} else {// 如果审批的结果为"不通过"
//			// 将申请的状态修改为“不通过”
//			application.setStatus(Application.STATUS_UNAPPROVED);
//			// 如果当前办理人不是最后一个节点，手动结束流程
//			if (processInstance != null) {
//				processEngine.getRuntimeService().deleteProcessInstance(
//						processInstanceId, Application.STATUS_UNAPPROVED);
//			}
//		}
	}

}
