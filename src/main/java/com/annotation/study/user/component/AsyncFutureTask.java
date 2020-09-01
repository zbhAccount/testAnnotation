package com.annotation.study.user.component;

import com.annotation.study.user.handle.AbstractHandle;
import com.annotation.study.user.utils.SpringUtils;
import com.annotation.study.user.vo.AsyncContext;
import com.annotation.study.user.vo.AsyncQueryVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class AsyncFutureTask<T> implements Callable<Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncFutureTask.class);
    // handle名称
    private String handleName = "girlHandle";
    // 结果
    private AsyncQueryVo<T> asyncQueryVo;
    // 参数
    private Map<String, Object> map;

    @Override
    public T call() throws Exception {
        LOGGER.error("异步线程future");
        AsyncContext.getInstance().set(map);
        // 根据名称获取bean
        AbstractHandle abstractHandle = (AbstractHandle)SpringUtils.getBean(handleName);
        // 执行方法
        abstractHandle.handle(asyncQueryVo);
        return asyncQueryVo.getT();
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }

    public AsyncQueryVo<T> getAsyncQueryVo() {
        return asyncQueryVo;
    }

    public void setAsyncQueryVo(AsyncQueryVo<T> asyncQueryVo) {
        this.asyncQueryVo = asyncQueryVo;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
