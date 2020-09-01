package com.annotation.study.user.vo;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 多线程传参数类
 */
@Slf4j
public class AsyncContext {

    private static AsyncContext asyncContext = null;
    private final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static AsyncContext getInstance() {
        if (asyncContext == null) {
            asyncContext = new AsyncContext();
        }
        return asyncContext;
    }

    public void set(Map<String, Object> params) {
        asyncContext.threadLocal.set(params);
    }

    public void clear() {
        asyncContext.threadLocal.remove();
    }

    public Map<String, Object> get() {
        log.info("{}",asyncContext.threadLocal.get());
        return asyncContext.threadLocal.get();
    }

}
