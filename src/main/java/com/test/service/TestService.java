package com.test.service;

import lombok.Data;

/**
 * @ClassName TestService
 * @Description TODO
 * @Author renhao
 * @Date 2019/7/8 15:46
 **/
@Data
public class TestService {
    private String name;
    public void print(){
        System.out.println("动态载入bean,name="+name);
    }
}
