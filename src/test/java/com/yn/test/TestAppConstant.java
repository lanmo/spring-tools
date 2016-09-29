package com.yn.test;

import com.yn.spring.constant.AppConstants;
import com.yn.spring.utils.L;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yangnan on 16/9/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestAppConstant {

    @Test
    public void testGetUrl() throws Exception {
        System.setProperty("env", "local");
        L.trace(AppConstants.url);
    }
}
