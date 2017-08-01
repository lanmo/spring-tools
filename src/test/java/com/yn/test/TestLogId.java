package com.yn.test;

import com.yn.keygen.DefaultKeyGenerator;
import com.yn.keygen.KeyGenerator;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yangnan on 17/7/31.
 */
public class TestLogId {

    private static Logger logger = LoggerFactory.getLogger(TestLogId.class);

    private static AtomicInteger count = new AtomicInteger(1);

    private static void log(String param) {
        logger.error("测试:[{}]", param);
    }

    private static KeyGenerator generator = new DefaultKeyGenerator();

    public static void main(String[] args) throws IOException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        ClassPathResource resource = new ClassPathResource("log4j.xml");
        System.out.println(resource.getFile().getAbsolutePath());
        DOMConfigurator.configure(resource.getFile().getAbsolutePath());
        MDC.put("test", String.valueOf(generator.generateKey()));
        System.out.println("test:" + MDC.get("test"));


        for (int i=0; i<10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    MDC.put("test", String.valueOf(generator.generateKey()));
                    log(String.valueOf(count.addAndGet(1)));
                    log(String.valueOf(count.addAndGet(1)) + " 测试");
                    threadLocal.set(count.get());
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        Map map = MDC.getCopyOfContextMap();
        System.out.println(map);
        log("反反复复");
//        for (Object key : map.keySet()) {
//            System.out.println("key=" + key + ",value=" + map.get(key));
//        }
    }

}
