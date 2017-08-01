package com.yn.spring.annotation;

import java.lang.annotation.*;

/**
 * Created by yangnan on 17/7/28.
 * 不为空判断
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotBlank {
    String message() default "";
}
