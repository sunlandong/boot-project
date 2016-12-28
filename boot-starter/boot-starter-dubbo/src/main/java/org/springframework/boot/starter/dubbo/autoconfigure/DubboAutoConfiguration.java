package org.springframework.boot.starter.dubbo.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


@Configuration
@Order
public class DubboAutoConfiguration {
	protected Logger logger = LoggerFactory.getLogger(DubboAutoConfiguration.class);
	
//	@Value("${shutdown.latch.domain.name: net.liuxuejia}")
//	private String shutdwonLatchDomainName;
	
	@Bean
	@ConditionalOnClass(name="com.alibaba.dubbo.rpc.Exporter")
	public DubboServiceLatchCommandLineRunner configureDubboServiceLatchCommandLineRunner() {
		
		DubboServiceLatchCommandLineRunner dubboServiceLatchCommandLineRunner = new DubboServiceLatchCommandLineRunner();
		//dubboServiceLatchCommandLineRunner.setDomain(shutdwonLatchDomainName);
		return dubboServiceLatchCommandLineRunner;
	}
}
