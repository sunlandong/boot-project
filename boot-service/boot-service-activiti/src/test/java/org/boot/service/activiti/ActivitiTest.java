package org.boot.service.activiti;

import java.util.UUID;

import org.boot.facede.activiti.model.Application;
import org.boot.facede.activiti.model.Template;
import org.boot.facede.activiti.service.ActivitiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiTest {
	
	
	@Autowired
	private ActivitiService activitiService;
	
	@Test
	public void deployTest() throws Exception {
//		activitiService.deploy(new File("C:/A/E/myeclipsews/boot-project/boot-service/boot-service-activiti/src/test/java/LeaveProcess.zip"), "请假流程测试");
//		activitiService.deploy(new File("C:/A/E/myeclipsews/boot-project/boot-service/boot-service-activiti/src/test/java/Money.zip"), "报销流程测试");
	}
	@Test
	public void getAllLastDeploymentTest() throws Exception {
//		List<ProcessDefinitionEntity> definitions = activitiService.getAllLastDeployment();
//	    for (ProcessDefinition processDefinition : definitions) {
//	    	System.out.println(processDefinition.getDeploymentId());
//		}
	}
	@Test
	public void deleteDeploymentTest() throws Exception {
		activitiService.deleteDeploymentByPDKey("Money");
	}
	@Test
	public void saveTemplateTest() throws Exception {
		Template template = new Template();
		template.setVcFilePath("group1/M00/00/07/Cu4SUlhh2Z6ATGkRAAAyvhx52d008.docx");
		template.setVcId(UUID.randomUUID().toString());
		template.setVcPdkey("myProcess");
		template.setVcName("请假流程模板");
		activitiService.saveTemplate(template);
	}
	@Test
	public void findAllTemplateTest() throws Exception {
		
		System.out.println(activitiService.findAllTemplate());
	}
	@Test
	public void findAllTemplateByIdTest() throws Exception {
		System.out.println(activitiService.findAllTemplateById("fafafadfadsfasdfas"));
	}
	@Test
	public void updateTemplateTest() throws Exception {
		Template template = new Template();
		template.setVcFilePath("/seven123.jpg");
		template.setVcId("fafafadfadsfasdfas");
		activitiService.updateTemplate(template);
	}
	@Test
	public void deleteTemplateTest() throws Exception {
		System.out.println(activitiService.deleteTemplate("fafafadfadsfasdfas"));
	}
	@Test
	public void submitApplicationTest() throws Exception {
		Application application = new Application();
		application.setVcTemplateId("91b960ca-825f-4b2d-aa2c-40a8ef330df1");
		application.setVcFilePath("group1/M00/00/07/Cu4SUlhh2Z6ATGkRAAAyvhx52d008.docx");
		application.setVcName("username + date_str");
		application.setVcStatus("审批中");
		application.setVcUserId(UUID.randomUUID().toString());
		application.setVcId(UUID.randomUUID().toString());
		activitiService.submitApplication(application);
	}
	@Test
	public void findApplicationByUserIdTest() throws Exception {
		System.out.println(activitiService.findApplicationByUserId("sevenfasfass"));
	}
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
	}
}
