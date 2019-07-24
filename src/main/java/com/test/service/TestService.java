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
    private  CustomService customService;
    public TestService(){}
    public TestService(CustomService customService){
        this.customService = customService;
    }

    private String name;
    public void print(){
        if (customService!=null){
            customService.show();
        }
        System.out.println("动态载入bean,name="+name);
    }
}
