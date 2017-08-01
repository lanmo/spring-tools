package com.yn.service.impl;

import com.yn.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yangnan on 17/8/1.
 */
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String validate(String userName, String password) {
        logger.info("server userName:[{}] password:[{}]", userName, password);
        return "server:" + userName + "," + password;
    }
}
