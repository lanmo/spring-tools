package com.yn.test;

import com.yn.service.UserService;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Created by yangnan on 17/8/1.
 */
public class TestDubboConsumer {
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("log4j.xml");
        try {
            System.out.println(resource.getFile().getAbsolutePath());
            DOMConfigurator.configure(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-dubbo-reference.xml");
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService.validate("眼哪跟哪", "123456"));
        System.out.println(userService.validate("ddd", "123456"));
    }
}
