package com.annotation.study.user.service.impl;

import com.annotation.study.user.mapper.UserMapper;
import com.annotation.study.user.model.User;
import com.annotation.study.user.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 测试事务传播机制，以 REQUIRED、SUPPORTS、REQUIRES_NEW 为例
 *                        同一个bean下(@Tranctional失效)                不同bean
 *
 * 当前没有事务,子REQUIRED -> 新建事务                                   回滚子
 * 当前有事务,子REQUIRED -> 当前事务                                     全部回滚
 * 当前没有事务,子SUPPORTS -> 非事务方式运行                             非事务方式运行
 * 当前有事务,子SUPPORTS -> 当前事务                                     全部回滚
 * 当前没有事务，子REQUIRES_NEW -> 新建事务                              回滚子
 * 当前有事务，子REQUIRES_NEW -> 挂起当前事务，新建事务                   全部回滚(若是对子进行捕获异常，回滚子)
 * 当前有事务，子没事务 ->  当前事务                                      全部回滚(若是对子进行捕获异常，子以无事务方式运行)
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    UserMapper userMapper;
    @Resource
    UserServiceImpl2 userServiceImpl2;

    @Override
    @Transactional
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateByPrimaryKey(User user) {
        userMapper.updateByPrimaryKey(user);
        return 10/0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation =  Propagation.REQUIRED)
    public int insertAndUpdate(User user) {
        this.insert(user);
        try{
            userServiceImpl2.insert(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        user.setId(505);
        this.insert(user);
        return 1;
    }

    /**
     * 捕获异常@Tractional失效
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class,propagation =  Propagation.REQUIRED)
    public int updateAndInsert(User user) {
        try{
            updateByPrimaryKey(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 1;
    }

}
