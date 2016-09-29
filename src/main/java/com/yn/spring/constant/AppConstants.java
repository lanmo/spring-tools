package com.yn.spring.constant;

import com.yn.spring.annotation.PropertyConfig;
import org.springframework.stereotype.Component;

/**
 * Created by yangnan on 16/9/28.
 */
@Component
public class AppConstants {

    @PropertyConfig("app.url")
    public static String url;

}
