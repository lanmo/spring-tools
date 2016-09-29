package com.yn.spring.annotation;

import java.lang.annotation.*;

/**
 * Created by yangnan on 16/9/28.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PropertyConfig {
    String value() default "";
}
