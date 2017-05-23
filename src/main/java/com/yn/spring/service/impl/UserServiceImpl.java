package com.yn.spring.service.impl;

import com.yn.spring.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by yangnan on 2017/5/17.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
