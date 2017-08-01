package com.yn.spring.service.impl;

import com.alibaba.fastjson.JSON;
import com.yn.spring.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangnan on 2017/5/17.
 */
@Service("userService")
public class UserServiceImpl1 implements UserService {
    @Override
    public int add(int a, int b) {
        try {
            Thread.sleep(1000 * 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a - b;
    }

    @Override
    public String print(String abc) {

        Map<String, String> map = new HashMap<>();
        map.put("abc", abc);
        map.put("ret", abc + ":dd");

        return JSON.toJSONString(map);
    }
}
