package com.test;

import org.junit.Assert;
import org.junit.Test;
import com.test.util.JUITUtils;

public class DemoJunitTest {

    @Test
    public void testGetHelloWorld() {
        Assert.assertEquals(JUITUtils.getHelloWorld(), "Hello World");
    }

}