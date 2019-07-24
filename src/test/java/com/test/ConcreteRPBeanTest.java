package com.test;

import com.test.componet.ConcreteRPBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName ConcreteRPBeanTest
 * @Description TODO
 * @Author renhao
 * @Date 2019/7/24 15:11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConcreteRPBeanTest {

    @Autowired
    private ConcreteRPBean concreteRPBean;

    @Test
    public void sayHello() throws Exception {
        concreteRPBean.sayHello();
    }

}
