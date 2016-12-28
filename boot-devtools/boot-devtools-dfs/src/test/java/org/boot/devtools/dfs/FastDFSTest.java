package org.boot.devtools.dfs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//如果有spring容器则打开一下配置
//@ContextConfiguration(locations = { "classpath*:spring/applicationContext*.xml" })
//@TransactionConfiguration
//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,TransactionalTestExecutionListener.class })
//@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FastDFSTest {

	private static final Logger logger = LoggerFactory.getLogger(FastDFSTest.class);

	/**
	 * spring实例注入
	 */
//	@Autowired
//	RiskMonitorBizBaseManager bizBaseManager;

	@Test
	public void uploadTest() {
		String filePath = "src/test/java/doctest.docx";
		File file = new File(filePath);
		String fileId = FastDFSClient.uploadFile(file, filePath);
//		assertNotNull(fileId);
		assertThat(fileId).isNotEmpty();
		logger.info("上传测试成功-fileId:" + fileId);
		 //删除测试
//		int result = FastDFSClient.deleteFile(fileId);
//		assertEquals(0, result);
//		assertThat(result).isEqualTo(0);
//		logger.info(result == 0 ? "删除测试成功" : "删除测试失败:" + result);
	}
	
	@Test
	public void downloadTest() {
		String fileId = "group1/M00/00/00/Cu4SUlcxVUGAHrf0AAT6FZWdr-c716.jpg";
		InputStream inputStream = FastDFSClient.downloadFile(fileId);
		File destFile = new File("C:/DownloadTest.jpg");
		try {
			FileUtils.copyInputStreamToFile(inputStream, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void deleteTest() {
		String fileId = "group1/M00/00/00/Cu4SUlcxVUGAHrf0AAT6FZWdr-c716.jpg";
		int result = FastDFSClient.deleteFile(fileId);
		System.out.println(result == 0 ? "删除成功" : "删除失败:" + result);
	}

}