package com.test.util;

/**
 * @ClassName Calculator
 * @Description TODO
 * @Author renhao
 * @Date 2019/6/28 15:45
 **/
public class Calculator2 {
    public int foo(int a,int b){
        int returnInt = 0 ;
        if(a<10){
            returnInt +=1;
        }
        if(b<10){
            returnInt +=10;
        }
        return returnInt;
    }
}
