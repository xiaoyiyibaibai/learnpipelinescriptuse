package com.test;

import com.test.util.Calculator;
import com.test.util.Calculator2;
import org.junit.Assert;
import org.junit.Test;

/**
 * @ClassName Calculator_Test
 * @Description TODO
 * @Author renhao
 * @Date 2019/6/28 15:46
 **/
public class Calculastor_Test {
    private Calculator2 instance = new Calculator2();


    @Test
    public void testSub() {
        int a = 10;
        int b = 20;
        int expected = -10;
        Assert.assertNotEquals(expected, instance.foo(a, b));
    }
}