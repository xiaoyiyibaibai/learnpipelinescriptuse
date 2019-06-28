package com.test.util;

/**
 * @ClassName MathUtil
 * @Description TODO
 * @Author renhao
 * @Date 2019/6/28 17:02
 **/
public class MathUtil3 {
    public static int max(int a, int b, int c){
        if(a > b){
            if(a > c){
                return a;
            }else{
                return c;
            }
        }else{
            if(b > c){
                return b;
            }else{
                return c;
            }
        }
    }
}