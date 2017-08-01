package com.yn.test;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yangnan on 17/8/1.
 */
public class TestDubboProvider {
    public static void main(String[] args) throws InterruptedException {
        ClassPathResource resource = new ClassPathResource("log4j.xml");
        try {
            System.out.println(resource.getFile().getAbsolutePath());
            DOMConfigurator.configure(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-dubbo-provider.xml");
        context.start();
        countDownLatch.await();
    }
}
