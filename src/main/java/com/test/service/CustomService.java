package com.test.service;

/**
 * @ClassName CustomService
 * @Description TODO
 * @Author renhao
 * @Date 2019/7/24 13:46
 **/
public class CustomService {
    public void show(){
        System.out.println("我是"+this.getClass().toString()+"的show方法！");
    }
}
