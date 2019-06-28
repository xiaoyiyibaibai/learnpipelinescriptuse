package com.test;

import com.test.util.Calculator;
import org.junit.Assert;
import org.junit.Test;
/**
 * @ClassName Calculator_Test
 * @Description TODO
 * @Author renhao
 * @Date 2019/6/28 15:46
 **/
public class Calculator_Test {
    private Calculator instance = new Calculator();

    @Test
    public void testAdd() {
        int a = 10;
        int b = 20;
        int expected = 30;
        Assert.assertEquals(expected, instance.add(a, b));
    }

    @Test
    public void testSub2() {
        int a = 10;
        int b = 20;
        int expected = -10;
        Assert.assertEquals(expected, instance.sub(a, b));
    }
}