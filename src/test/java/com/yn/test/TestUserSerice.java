package com.yn.test;

import com.yn.spring.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yangnan on 2017/5/17.
 */
public class TestUserSerice {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "bean2.xml", "bean1.xml");
        applicationContext.setAllowBeanDefinitionOverriding(false);

        UserService userService = applicationContext.getBean(UserService.class);
        int c = userService.add(1, 2);
        System.out.println(c);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();
    }
}
