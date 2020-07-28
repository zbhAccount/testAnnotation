package com.annotation.study.user.mapper;


import com.annotation.study.user.model.User;

import java.util.List;

public interface UserMapper {

    List<User> findAll();

    int insert(User record);

    int updateByPrimaryKey(User record);
}
