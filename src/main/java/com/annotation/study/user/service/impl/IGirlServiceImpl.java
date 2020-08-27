package com.annotation.study.user.service.impl;

import com.annotation.study.user.model.User;
import com.annotation.study.user.service.IGirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IGirlServiceImpl implements IGirlService {
    @Resource
    private ThreadPoolTaskExecutor createServiceExecutor;
    @Resource
    private ThreadPoolTaskExecutor updateServiceExecutor;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Override
    public int insert(final User user) {
        final User user1 = (User) user.clone();
        user1.setSex(0);
        createServiceExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userServiceImpl.insert(user1);
            }
        });
        return 1;
    }

    @Override
    public int update(User user) {
        updateServiceExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(" ");
            }
        });
        return 1;
    }
}
