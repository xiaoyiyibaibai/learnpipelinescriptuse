package com.test.componet;

import org.springframework.stereotype.Component;

/**
 * @ClassName PostBean
 * @Description TODO
 * @Author renhao
 * @Date 2019/7/24 15:01
 **/
@Component
public class PostBean {

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void sayHello() {
        System.out.println(String.format("author %s say hello!", author));
    }
}