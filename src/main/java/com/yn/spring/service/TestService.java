package com.yn.spring.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yangnan on 2017/5/17.
 */
@Service
public class TestService {

    private UserService userService;

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void print() {
        userService.add(1, 2);
    }
}
