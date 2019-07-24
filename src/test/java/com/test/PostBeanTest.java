package com.test;

import com.test.componet.PostBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostBeanTest {

    @Autowired
    private PostBean postBean;

    @Test
    public void sayhello() throws Exception {
        postBean.sayHello();
    }

}