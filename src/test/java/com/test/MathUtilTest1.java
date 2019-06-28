package com.test;

import static org.junit.Assert.*;

import com.test.util.MathUtil;
import org.junit.Test;
public class MathUtilTest1 {
    @Test
    public void test_max_1_2_3() {
        assertEquals(3, MathUtil.max(1, 2, 3));
    }
}