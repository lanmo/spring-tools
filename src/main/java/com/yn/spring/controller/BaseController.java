package com.yn.spring.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangnan on 16/9/27.
 */
@RestController
public class BaseController {

    @ExceptionHandler
    public void exception(Throwable e) {
        e.printStackTrace();
    }
}
