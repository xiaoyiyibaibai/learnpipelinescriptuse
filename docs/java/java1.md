参考： [Spring动态 注入/删除 Bean](https://blog.csdn.net/m0_37556444/article/details/84876063)

# Spring动态 注入/删除 Bean

我们通过getBean来获得对象,但这些对象都是事先定义好的,我们有时候要在程序中动态的加入对象.
因为如果采用配置文件或者注解，我们要加入对象的话,还要重启服务,如果我们想要避免这一情况就得采用动态处理bean,
包括：动态注入,动态删除。

## 1. 动态注入bean思路

在具体进行代码实现的时候，我们要知道，Spring管理bean的对象是BeanFactory，具体的是DefaultListableBeanFactory，
在这个类当中有一个注入bean的方法：registerBeanDefinition，在调用registerBeanDefinition方法时，需要BeanDefinition参数，那么这个参数怎么获取呢？
Spring提供了BeanDefinitionBuilder可以构建一个BeanDefinition，那么我们的问题就是如何获取BeanFactory了，
这个就很简单了，只要获取到ApplicationContext对象即可获取到BeanFacory了。

## 2. 动态注入实现代码

综上所述，如果我们要编写一个简单里的例子的话，那么分以个几个步骤进行编码即可进行动态注入了：

1. 获取ApplicationContext;
2. 通过ApplicationContext获取到BeanFacotory;
3. 通过BeanDefinitionBuilder构建BeanDefiniton;
4. 调用beanFactory的registerBeanDefinition注入beanDefinition；
5. 使用ApplicationContext.getBean获取bean进行测试；

我们需要先定义个类进行测试，比如TestService代码如下：

```aidl
package com.kfit.demo.service;

public class TestService {

    private String name;

    public String getName() {
       return name;
    }

    public void setName(String name) {
       this.name = name;
    }

    public void print(){
       System.out.println("动态载入bean,name="+name);
    }
}

```

那么下面我们的目标就是动态注入TestService了，根据以上的分析，我们进行编码，具体代码如下：

```aidl

    //获取context. 
    ApplicationContext ctx =  (ApplicationContext) SpringApplication.run(App.class, args);
    
    //获取BeanFactory
    DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory)ctx.getAutowireCapableBeanFactory();
    
       
    //创建bean信息
    BeanDefinitionBuilderbeanDefinitionBuilder =BeanDefinitionBuilder.genericBeanDefinition(TestService.class);
    beanDefinitionBuilder.addPropertyValue("name","张三");
    
      
    //动态注册bean
    defaultListableBeanFactory.registerBeanDefinition("testService",beanDefinitionBuilder.getBeanDefinition());
    
    //获取动态注册的bean
    TestService testService =ctx.getBean(TestService.class);
    testService.print();

```
执行代码

> 动态载入bean,name=张三

到这里，就证明我们的代码很成功了。

## 3. 多次注入同一个bean的情况

多次注入同一个bean的，如果beanName不一样的话，那么会产生两个Bean；如果beanName一样的话，后面注入的会覆盖前面的。

第一种情况：beanName一样的代码：

```aidl
   beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(TestService.class);
   beanDefinitionBuilder.addPropertyValue("name","李四");
   defaultListableBeanFactory.registerBeanDefinition("testService", beanDefinitionBuilder.getBeanDefinition());
```
运行看控制台：

```aidl
动态载入bean,name=李四
```

第二种情况：beanName不一样的代码：

```aidl
    beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(TestService.class);
    beanDefinitionBuilder.addPropertyValue("name","李四");
    defaultListableBeanFactory.registerBeanDefinition("testService1",beanDefinitionBuilder.getBeanDefinition());
    TestService testService =ctx.getBean(TestService.class);
    testService.print();
```


此时如果没有更改别的代码直接运行的话，是会报如下错误的：

```aidl
Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type [com.kfit.demo.service.TestService] is defined: expected single matching bean but found 2: testService1,testService

```

大体意思就是在按照 byType getBean的时候，找到了两个bean，这时候就不知道要获取哪个了，所以在获取的时候，我们就要使用byName指定我们是要获取的testService还是testService1，只需要修改一句代码：

```aidl
TestService testService =ctx.getBean("testService");
```

一般重复注入一个新Bean的情况较少，多数情况都是讲已有的Bean注入到容器中，

```aidl

applicationContext.getAutowireCapableBeanFactory().applyBeanPostProcessorsAfterInitialization(obj, obj.getClass().getName());
beanFactory.registerSingleton(obj.getClass().getName(), obj);

```

第一行：让obj完成Spring初始化过程中所有增强器检验，只是不重新创建obj，
第二行：将obj以单例的形式入驻到容器中，此时通过obj.getClass().getName()或obj.getClass()都可以拿到放入Spring容器的Bean。


## 4. 动态删除

相对于动态注入，动态删除就很简单了，直接奉上代码：

```aidl
 //删除bean.
defaultListableBeanFactory.removeBeanDefinition("testService");
```

