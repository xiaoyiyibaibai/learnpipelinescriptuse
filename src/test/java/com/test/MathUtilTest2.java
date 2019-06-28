package com.test;
import static org.junit.Assert.*;

import com.test.util.MathUtil;
import com.test.util.MathUtil2;
import org.junit.Test;
public class MathUtilTest2 {
    @Test
    public void test_max_1_2_3() {
        assertEquals(3, MathUtil2.max(1, 2, 3));
    }
    @Test
    public void test_max_10_20_30() {
        assertEquals(30, MathUtil2.max(10, 20, 30));
    }
    @Test
    public void test_max_100_200_300() {
        assertEquals(300, MathUtil2.max(100, 200, 300));
    }
}