package com.test;

import com.test.service.TestService;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, DataSourceAutoConfiguration.class })
public class Demo {

	public static void main(String[] args) {

		SpringApplication.run(Demo.class, args);
//		ConfigurableApplicationContext ctx = SpringApplication.run(Demo.class, args);
//		//获取BeanFactory
//		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) ctx.getAutowireCapableBeanFactory();
//
//       //创建bean信息.
//		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition( TestService.class);
//		beanDefinitionBuilder.addPropertyValue("name","张三");
//
//       //动态注册bean.
//		defaultListableBeanFactory.registerBeanDefinition("testService", beanDefinitionBuilder.getBeanDefinition());
//
////		多次注入同一个bean的，如果beanName不一样的话，那么会产生两个Bean；如果beanName一样的话，后面注入的会覆盖前面的。
//
//		//第一种情况：beanName一样的代码：
//		beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(TestService.class);
//		beanDefinitionBuilder.addPropertyValue("name","李四");
//		defaultListableBeanFactory.registerBeanDefinition("testService", beanDefinitionBuilder.getBeanDefinition());
//
//		// 第二种情况：beanName不一样的代码：
//		beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(TestService.class);
//		beanDefinitionBuilder.addPropertyValue("name","李四");
//		defaultListableBeanFactory.registerBeanDefinition("testService1", beanDefinitionBuilder.getBeanDefinition());
//
//		try {
//			TestService testService =ctx.getBean(TestService.class);
//			testService.print();
//		}catch (Exception e){
//			TestService testService2 = (TestService)ctx.getBean("testService");
//			testService2.print();
//		}
//
//		//动态删除；相对于动态注入，动态删除就很简单了，直接奉上代码：
//		defaultListableBeanFactory.removeBeanDefinition( "testService" );
//		TestService testService2 = (TestService)ctx.getBean("testService");
//		if (testService2==null){
//			System.out.println(testService2);
//		}

	}
}
