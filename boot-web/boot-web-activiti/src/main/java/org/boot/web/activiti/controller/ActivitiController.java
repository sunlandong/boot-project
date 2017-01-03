package org.boot.web.activiti.controller;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.boot.common.core.utils.ErrorInfo;
import org.boot.common.core.utils.ResultUtils;
import org.boot.devtools.dfs.FastDFSClient;
import org.boot.facede.activiti.model.Application;
import org.boot.facede.activiti.model.ApproveInfo;
import org.boot.facede.activiti.model.ApproveInfoVo;
import org.boot.facede.activiti.model.Template;
import org.boot.facede.activiti.service.ActivitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/workflow")
public class ActivitiController {
	
	@Autowired
	ActivitiService activitiService;
	
	/**
	 * 部署流程
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deploy",method=RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<String> deploy(MultipartFile file,String bpmnName,HttpServletRequest request)throws Exception{
		activitiService.deploy(bpmnName, file.getBytes());
		return ResultUtils.createSuccess("ok");
	}
	/**
	 * 分离型流程部署  未完成
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/isolateDeploy",method=RequestMethod.PUT)
	@ResponseBody
	public ErrorInfo<String> isolateDeploy(@RequestParam(required = false) MultipartFile file,String bpmnName, HttpServletRequest request)throws Exception{
		//File file = new File("C:/A/E/myeclipsews/boot-project/boot-web/boot-web-activiti/src/main/resources/FirstProcess.zip");
		ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream());
		File tmpFile = new File("C:/A/E/myeclipsews/boot-project/boot-web/boot-web-activiti/src/main/resources/tmp" + System.currentTimeMillis() + ".zip");
		file.transferTo(tmpFile);
		ZipFile zipFile = new ZipFile(tmpFile);
		InputStream pngStream = null;
		InputStream bpmnStream = null;
		ZipEntry zipEntry;  
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {  
            if (zipEntry.isDirectory()) {
            	
            } else {  
            	if(zipEntry.getName().endsWith("png")) {
            		pngStream = zipFile.getInputStream(zipEntry);
            	} else if(zipEntry.getName().endsWith("bpmn")){
            		bpmnStream = zipFile.getInputStream(zipEntry);
            	}
            } 
        }
        //fastdfs存储文件
        String pngPath = "";
        String bpmnPath = "";
		activitiService.isolateDeploy(bpmnName, pngPath, bpmnPath, pngStream, bpmnStream);
		return ResultUtils.createSuccess("ok");
	}
	
	/**
	 * 获取所有的最新版本的流程
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getAllLastDeployment",method=RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> getAllLastDeployment() throws Exception{
		return activitiService.getAllLastDeployment();
	}
	
	
	@RequestMapping(value="/getProcessDiagram/{pdid}",method=RequestMethod.GET)
	@ResponseBody
	public void getProcessDiagram(HttpServletResponse response,@PathVariable("pdid") String pdid) throws Exception{
		byte[] data = activitiService.showImages(pdid);
		response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
	}
	
	
	
	/**
	 * 根据流程定义KEY删除流程定义
	 * @param pdKey
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteDeploymentByPDKey/{pdKey}",method=RequestMethod.DELETE)
	@ResponseBody
	public ErrorInfo<String> deleteDeploymentByPDKey(@PathVariable("pdKey")String pdKey) throws Exception{
		activitiService.deleteDeploymentByPDKey(pdKey);
		return ResultUtils.createSuccess("ok");
	}
	
	/**
	 * 部署数据模板
	 * @param template
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deployTemplate", method=RequestMethod.POST)
	@ResponseBody
	public List<Template> deployTemplate(@RequestParam(required = false) MultipartFile file, Template template) throws Exception {
		template.setVcFilePath(FastDFSClient.uploadFile(file.getBytes(), file.getOriginalFilename()));
		template.setVcId(UUID.randomUUID().toString());
		activitiService.saveTemplate(template);
		return activitiService.findAllTemplate();
	}
	
	/**
	 * 根据模板ID删除数据模板
	 * @param templateId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteTemplate/{templateId}", method=RequestMethod.DELETE)
	@ResponseBody
	public ErrorInfo<String> deleteTemplate(@PathVariable("templateId")String templateId) throws Exception{
		String docFilePath = activitiService.deleteTemplate(templateId);
		//在fastdfs中删除文件
		return ResultUtils.createSuccess("ok");
	}
	
	/**
	 * 获取所有的数据模板
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findAllTemplate", method=RequestMethod.GET)
	@ResponseBody
	public List<Template> findAllTemplate() throws Exception{
		return activitiService.findAllTemplate();
	}
	
	/**
	 * 提交申请
	 * @param application
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/submitApplication", method=RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<String> submitApplication(@RequestBody Application application) throws Exception{
		application.setVcFilePath("group1/M00/00/07/Cu4SUlhh2Z6ATGkRAAAyvhx52d008.docx");
		application.setVcId(UUID.randomUUID().toString());
		application.setVcName("测试申请名称");
		//有注册登录过后获取USERID
		application.setVcUserId("878fe183-8830-436d-b4d7-8fade408760e");
		activitiService.submitApplication(application);
		return ResultUtils.createSuccess("ok");
	}
	
	/**
	 * 根据用户ID获取登录用户的流程申请
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findApplication", method=RequestMethod.GET)
	@ResponseBody
	public List<Application> findApplication(String status) throws Exception{
		//在redis中找到登录用户的ID
		String userId = "878fe183-8830-436d-b4d7-8fade408760e";
		return activitiService.findApplicationByUserId(userId);
	}
	
	/**
	 * 根据申请ID获取审批信息列表
	 * @param applicationId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findApproveInfo/{applicationId}", method=RequestMethod.GET)
	@ResponseBody
	public List<ApproveInfo> findApproveInfo(@PathVariable("applicationId")String applicationId) throws Exception{
		//在redis中找到登录用户的ID 加强权限判断
		return activitiService.findApproveInfoByApplicationId(applicationId);
	}
	
	/**
	 * 根据登录用户ID获取用户待办事项
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findTaskView", method=RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> findTaskView() throws Exception{
		//在redis中找到登录用户的ID
		String userId = "xiaoyuan";
		return activitiService.findTaskViewByUserId(userId);
	}
	
	/**
	 * 审批待办任务
	 * @param approveInfoVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/approve", method=RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<String> approve(@RequestBody ApproveInfoVo approveInfoVo) throws Exception{
		//在redis中找到登录用户的ID
		ApproveInfo approveInfo = approveInfoVo.getApproveInfo();
		approveInfo.setVcUserId("xiaoyuan");
		activitiService.approve(approveInfo, approveInfoVo.getTaskId());
		return ResultUtils.createSuccess("ok");
	}
}
