package com.yn.spring.controller;

import com.yn.spring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by yangnan on 16/9/27.
 */
@Controller
@RequestMapping("home")
public class HomeController extends BaseController {

    @Resource
    private UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public Object test() {
        try {
            userService.add(1,2);
            userService.print("哈哈哈");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new T();
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
