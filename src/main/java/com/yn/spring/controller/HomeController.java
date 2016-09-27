package com.yn.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yangnan on 16/9/27.
 */
@Controller
@RequestMapping("home")
public class HomeController extends BaseController {

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
