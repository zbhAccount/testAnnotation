package com.annotation.study.user.handle;

import com.annotation.study.user.vo.AsyncQueryVo;

public interface AbstractHandle {

    public void handle(AsyncQueryVo asyncQueryVo);
}
