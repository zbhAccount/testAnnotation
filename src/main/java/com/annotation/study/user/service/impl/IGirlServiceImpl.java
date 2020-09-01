package com.annotation.study.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.annotation.study.user.component.AsyncFutureTask;
import com.annotation.study.user.configuration.ThreadPoolExecutorExtend;
import com.annotation.study.user.model.User;
import com.annotation.study.user.service.IGirlService;
import com.annotation.study.user.vo.AsyncContext;
import com.annotation.study.user.vo.AsyncQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
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
    public Integer getCount(User user) {
        log.info("getCount参数:{}", JSON.toJSONString(user));
        return 10;
    }

    public void execute() {
        Map<String, Object> map = new HashMap<>();
        map.put("param", new User(1,"小小",1,2));
        AsyncFutureTask<Integer> asyncFutureTask = new AsyncFutureTask<>();
        asyncFutureTask.setMap(map);
        asyncFutureTask.setHandleName("girlHandle");
        Map<String, Integer> resultMap = new HashMap<>();
        AsyncQueryVo asyncQueryVo = AsyncQueryVo.builder().t(resultMap).build();
        asyncFutureTask.setAsyncQueryVo(asyncQueryVo);
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
