package com.yn.spring.annotation;

import java.lang.annotation.*;

/**
 * Created by yangnan on 17/7/28.
 * 表单数据
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Form {
}
