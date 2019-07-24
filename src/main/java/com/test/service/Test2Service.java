package com.test.service;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import springfox.documentation.service.ApiListing;

import java.util.Set;

/**
 * @ClassName Test2Service
 * @Description TODO
 * @Author renhao
 * @Date 2019/7/24 14:45
 **/
public class Test2Service implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
//        registry.registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
//        registry.getBeanDefinitionNames();
//        registry.isBeanNameInUse( String );
//        registry.containsBeanDefinition( String  );
//        registry.removeBeanDefinition( String );
//        registry.getBeanDefinitionCount();
//        registry.registerAlias(String name, String alias);
//        String[] getAliases(String name);
        // Set<MethodMetadata> getAnnotatedMethods(String annotationName);



    }
}
