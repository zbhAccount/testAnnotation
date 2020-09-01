package com.annotation.study.user.service.impl;

import com.annotation.study.user.component.AsyncFutureTask;
import com.annotation.study.user.configuration.ThreadPoolExecutorExtend;
import com.annotation.study.user.model.User;
import com.annotation.study.user.service.IGirlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@Service
public class IGirlServiceImpl implements IGirlService {
    @Resource
    private ThreadPoolTaskExecutor createServiceExecutor;
    @Resource
    private ThreadPoolTaskExecutor updateServiceExecutor;
    @Resource
    private ThreadPoolExecutorExtend asyncServiceExecutor;
    @Resource
    private UserServiceImpl userServiceImpl;

    /**
     * 不使用@Async注解
     *
     * @param user
     * @return
     */
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

    /**
     * 使用@Async註解
     *
     * @param user
     * @return
     */
    @Async("updateServiceExecutor")
    @Override
    public void update(User user) {
        Thread t = Thread.currentThread();
        log.info("多线程：{}", t.getName());
    }


    /**
     * 使用@Async註解
     *
     * @param user
     * @return
     */
//    @Async("asyncServiceExecutor")
    @Override
    public void async(User user) {
        Thread t = Thread.currentThread();
        log.info("多线程：{}", t.getName());
    }

    @Override
    public Integer getCount() {
        return 10;
    }

    public void execute() {
        AsyncFutureTask<Integer> asyncFutureTask = new AsyncFutureTask<Integer>();
        Future future = asyncServiceExecutor.submit(asyncFutureTask);
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
