package org.boot.web.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath*:/spring/**/*.xml")
public class BootStrap {
//	@Bean
//    public HttpMessageConverters fastJsonHttpMessageConverters() {
//       FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//       FastJsonConfig fastJsonConfig = new FastJsonConfig();
//       fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//       fastConverter.setFastJsonConfig(fastJsonConfig);
//       HttpMessageConverter<?> converter = fastConverter;
//       return new HttpMessageConverters(converter);
//    }
	public static void main(String[] args) {
		SpringApplication.run(BootStrap.class, args); 
	}
}
