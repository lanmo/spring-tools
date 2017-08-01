package com.yn.spring.controller;

import com.yn.service.UserService;
import com.yn.spring.annotation.Form;
import com.yn.spring.param.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by yangnan on 16/9/27.
 */
@Controller
@RequestMapping("home")
public class HomeController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Resource
    private UserService userService;
    private int count = 0;

    @RequestMapping("/test")
    @ResponseBody
    public Object test() {
        try {
            Thread.sleep(2 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new T();
    }

    @RequestMapping("/test1")
    @ResponseBody
    public Object test1(@Form User user) {
        logger.info("业务处理");
        userService.validate(String.valueOf(count++), String.valueOf(count++));
        return user;
    }

    @PostConstruct
    private void init() {
        logger.info("ddddd");
    }

    class T {
        private int a = 1;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }
}
