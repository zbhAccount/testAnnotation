package com.annotation.study.user.component;

import com.annotation.study.user.handle.AbstractHandle;
import com.annotation.study.user.utils.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class AsyncFutureTask<T> implements Callable<Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncFutureTask.class);

    @Override
    public T call() throws Exception {
        LOGGER.error("异步线程future");
        // 根据名称获取bean
        AbstractHandle abstractHandle = (AbstractHandle)SpringUtils.getBean("girlHandle");
        // 执行方法
        return (T)abstractHandle.handle();
    }
}
