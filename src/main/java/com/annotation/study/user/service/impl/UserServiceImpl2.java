package com.annotation.study.user.service.impl;

import com.annotation.study.user.mapper.UserMapper;
import com.annotation.study.user.model.User;
import com.annotation.study.user.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl2 implements IUserService {
    @Resource
    UserMapper userMapper;

    @Override
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public int insert(User user) {
        user.setId(500);
        userMapper.insert(user);
        return 1/0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public int updateByPrimaryKey(User user) {
        userMapper.updateByPrimaryKey(user);
        return 10/0;
    }

    @Override
    public int insertAndUpdate(User user) {
        return 0;
    }

    @Override
    public int updateAndInsert(User user) {
        return 0;
    }

}
