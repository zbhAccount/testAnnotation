package com.annotation.study.user.service;

import com.annotation.study.user.model.User;

public interface IUserService {

    public int insert(User user);

    public int updateByPrimaryKey(User user);

    public int insertAndUpdate(User user);

    public int updateAndInsert(User user);
}
