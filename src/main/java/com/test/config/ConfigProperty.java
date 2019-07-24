package com.test.config;

import lombok.Data;

/**
 * @ClassName ConfigProperty
 * @Description TODO
 * @Author renhao
 * @Date 2019/7/24 13:36
 **/
@Data
public  class ConfigProperty{
    private String host;
    private int port;
    private String userName;
    private String password;
}