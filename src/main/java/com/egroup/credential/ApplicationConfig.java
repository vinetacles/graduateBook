package com.egroup.credential;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;


/**
 * production  正式
 * release     測試伺服器 | 正式資料庫
 * test        測試
 * develop     開發
 */
@Configurable
@ComponentScan
public class ApplicationConfig {
	private static final String TYPE = "develop";
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	  final PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
	  final YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
	  final ClassPathResource classPathResource = new ClassPathResource("META-INF/" + TYPE + ".yml");
	  if (classPathResource.exists()) {
		  yamlPropertiesFactoryBean.setResources(classPathResource);
		  propertySourcesPlaceholderConfigurer.setProperties(yamlPropertiesFactoryBean.getObject());
	  } else {
		  System.out.println("找不到環境設定檔：" + "META-INF/" + TYPE + ".yml");
	  }
	  return propertySourcesPlaceholderConfigurer;
	}
}
