package com.marspie.demo;

import org.springframework.stereotype.Component;

/**
 * 
 * @author alex.yao
 *
 */
@Component
public class HelloWorld {

    public String sayHello(){
        System.out.println("被调用了>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return "hello world!";
    }

}
