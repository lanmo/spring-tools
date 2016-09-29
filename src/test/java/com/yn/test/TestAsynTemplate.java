package com.yn.test;

import com.yn.spring.service.AsyncRestTemplateService;
import com.yn.spring.service.RestTemplateService;
import com.yn.spring.utils.L;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangnan on 16/9/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestAsynTemplate {

    @Resource
    private AsyncRestTemplateService asyncRestTemplateService;

    @Resource
    private RestTemplateService restTemplateService;

    @Test
    public void testGetUrl() throws Exception {
        String url = "http://localhost:8080/home/test";
        Map<String, Object> map = new HashMap<>();
        map.put("1", 1);
//        asyncRestTemplateService.getForObject(url, map, String.class);
//        Map<String, Object> map1 = new HashMap<>();
//        map1.put("POST", "POST");
//        asyncRestTemplateService.postForObject(url, map1, String.class);
////        String str = restTemplateService.getForObject(url, String.class);
////        L.trace(str);

        for (int i=0;i<10000; i++) {
            map.put("i=" + i, i);
            asyncRestTemplateService.getForObject(url, map, String.class);
            L.trace(i);
        }
        Thread.sleep(60 * 1000);
    }
}
