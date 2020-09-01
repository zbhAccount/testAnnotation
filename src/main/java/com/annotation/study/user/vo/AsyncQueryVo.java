package com.annotation.study.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 异步查询vo类
 * 用于存储异步调用结果
 * @author bohan.zhou
 */
@Data
@Builder
@AllArgsConstructor
public class AsyncQueryVo<T> {

    private String name;

    private String type;

    private T t;

    public AsyncQueryVo(T t) {
        this.t = t;
    }

    public AsyncQueryVo(T t, String type) {
        this.t = t;
        this.type = type;
    }

    public AsyncQueryVo(T t, String type, String name) {
        this.t = t;
        this.type = type;
        this.name = name;
    }
}
