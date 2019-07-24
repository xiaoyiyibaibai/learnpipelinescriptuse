package com.test.config;

import com.test.service.CustomService;
import com.test.service.TestService;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ConfigurationConfig
 * @Description 加载优先级  构造函数》》》@ Autowired 》》》@PostConstruct
 * @Author renhao
 * @Date 2019/7/24 11:46
 **/
@Configuration
public class ConfigurationConfig {

    @Component
    @ConfigurationProperties(value = "xiaoren.custom")
    @Data
    public static class CustomConfigProperties {
        private String primary;
        private boolean enable;
        private Map<String, ConfigProperty> configs = new HashMap<>();
    }


    @Configuration
    public static class CustomConfig implements ApplicationContextAware {
        private ApplicationContext applicationContext;
        private final CustomConfigProperties customConfigProperties;

        @Autowired
        public CustomConfig(CustomConfigProperties customConfigProperties) {
            this.customConfigProperties = customConfigProperties;
        }

        @PostConstruct
        public void postConstruct() {
            ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext)this.applicationContext;
//            configurableApplicationContext.addApplicationListener(  );
//            configurableApplicationContext.addBeanFactoryPostProcessor(  );
//            configurableApplicationContext.addProtocolResolver(  );
            // configurableApplicationContext.refresh();
           // ConfigurableEnvironment configurableEnvironment =configurableApplicationContext.getEnvironment();
           //  configurableApplicationContext.registerShutdownHook();

            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
            CustomService customService = new CustomService();
            BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition( TestService.class ).addConstructorArgValue( customService ).getRawBeanDefinition();
            BeanDefinition beanDefinition2 = BeanDefinitionBuilder.genericBeanDefinition( TestService.class ).addConstructorArgValue( customService ).getBeanDefinition();

            beanFactory.registerBeanDefinition( "beanDefinition_one",beanDefinition );
            beanFactory.registerBeanDefinition( "beanDefinition_two",beanDefinition2 );

        }


        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }
    }

}


