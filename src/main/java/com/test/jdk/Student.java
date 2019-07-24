package com.test.jdk;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Student
 * @Description 学生接口实现类
 * @Author renhao
 * @Date 2019/7/8 17:45
 **/
@Slf4j
public class Student implements IStudent {
    @Override
    public Object studentInfo() {
        log.info("学生接口实现类");
        return "学生接口实现类";
    }
}
