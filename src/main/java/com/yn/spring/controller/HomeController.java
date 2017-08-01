package com.yn.spring.controller;

<<<<<<< HEAD
import com.yn.service.UserService;
import com.yn.spring.annotation.Form;
import com.yn.spring.param.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
=======
import com.yn.spring.service.UserService;
>>>>>>> c3d760a3ed890a056dfb5b4d66c806dd0ead7c3a
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

<<<<<<< HEAD
import javax.annotation.PostConstruct;
=======
>>>>>>> c3d760a3ed890a056dfb5b4d66c806dd0ead7c3a
import javax.annotation.Resource;

/**
 * Created by yangnan on 16/9/27.
 */
@Controller
@RequestMapping("home")
public class HomeController extends BaseController {

<<<<<<< HEAD
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Resource
    private UserService userService;
    private int count = 0;
=======
    @Resource
    private UserService userService;
>>>>>>> c3d760a3ed890a056dfb5b4d66c806dd0ead7c3a

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
