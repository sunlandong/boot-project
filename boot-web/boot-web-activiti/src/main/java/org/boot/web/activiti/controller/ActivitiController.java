package org.boot.web.activiti.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.boot.facede.activiti.model.Application;
import org.boot.facede.activiti.model.Template;
import org.boot.facede.activiti.service.ActivitiService;
import org.boot.web.activiti.util.ErrorInfo;
import org.boot.web.activiti.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/workflow")
public class ActivitiController {
	
	@Autowired
	ActivitiService activitiService;
	
	@RequestMapping(value="/deploy",method=RequestMethod.PUT)
	@ResponseBody
	public ErrorInfo<String> deploy(HttpServletRequest request)throws Exception{
		activitiService.deploy(new File("C:/A/E/myeclipsews/boot-project/boot-web/boot-web-activiti/src/main/resources/FirstProcess.zip"), "测试流程");
		return ResultUtils.createSuccess("ok");
	}
	
	@RequestMapping(value="/getAllLastDeployment",method=RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> getAllLastDeployment() throws Exception{
		return activitiService.getAllLastDeployment();
	}
	
	@RequestMapping(value="/deleteDeploymentByPDKey/{pdKey}",method=RequestMethod.DELETE)
	@ResponseBody
	public ErrorInfo<String> deleteDeploymentByPDKey(@PathVariable("pdKey")String pdKey) throws Exception{
		activitiService.deleteDeploymentByPDKey(pdKey);
		return ResultUtils.createSuccess("ok");
	}
	
	@RequestMapping(value="/deployTemplate", method=RequestMethod.POST)
	@ResponseBody
	public List<Template> deployTemplate(@RequestBody Template template) throws Exception {
		template.setVcFilePath("group1/M00/00/07/Cu4SUlhh2Z6ATGkRAAAyvhx52d008.docx");
		template.setVcId(UUID.randomUUID().toString());
		activitiService.saveTemplate(template);
		return activitiService.findAllTemplate();
	}
	
	@RequestMapping(value="/deleteTemplate/{templateId}", method=RequestMethod.DELETE)
	@ResponseBody
	public ErrorInfo<String> deleteTemplate(@PathVariable("templateId")String templateId) throws Exception{
		String docFilePath = activitiService.deleteTemplate(templateId);
		//在fastdfs中删除文件
		return ResultUtils.createSuccess("ok");
	}
	
	@RequestMapping(value="/findAllTemplate", method=RequestMethod.GET)
	@ResponseBody
	public List<Template> findAllTemplate() throws Exception{
		return activitiService.findAllTemplate();
	}
	
	@RequestMapping(value="/submitApplication", method=RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<String> submitApplication(@RequestBody Application application) throws Exception{
		application.setVcFilePath("group1/M00/00/07/Cu4SUlhh2Z6ATGkRAAAyvhx52d008.docx");
		application.setVcId(UUID.randomUUID().toString());
		//有注册登录过后获取USERID
		application.setVcUserId(UUID.randomUUID().toString());
		activitiService.submitApplication(application);
		return ResultUtils.createSuccess("ok");
	}
	
	
	

}
