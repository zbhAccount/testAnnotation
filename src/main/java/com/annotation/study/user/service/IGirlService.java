package com.annotation.study.user.service;

import com.annotation.study.user.model.User;

public interface IGirlService {

    public int insert(User user);

    public void update(User user);

    public void async(User user);

    public Integer getCount();

    public void execute();

}
