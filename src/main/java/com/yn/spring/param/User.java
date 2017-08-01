package com.yn.spring.param;

import com.alibaba.fastjson.JSON;
import com.yn.spring.annotation.NotBlank;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;

/**
 * Created by yangnan on 17/7/28.
 */
public class User {

    @NotBlank(message = "必须要有值在在在")
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        User user = new User();
    }


}
