package com.test.componet;

/**
 * @ClassName ConcreteRPBean
 * @Description TODO
 * @Author renhao
 * @Date 2019/7/24 15:10
 **/
public class ConcreteRPBean {

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void sayHello(){
        System.out.println(String.format("ConcreteRPBean call sayhello method ==> author %s say hello!", author));
    }
}
