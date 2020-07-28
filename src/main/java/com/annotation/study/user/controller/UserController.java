package com.annotation.study.user.controller;


import com.annotation.study.user.mapper.UserMapper;
import com.annotation.study.user.model.ImportUser;
import com.annotation.study.user.model.NewUser;
import com.annotation.study.user.model.User;
import com.annotation.study.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
@Import({ImportUser.class})
public class UserController {

    @Resource
    UserMapper userMapper;
    @Resource
    UserServiceImpl userService;
    @Autowired
    User userByConfiguretion;
    @Autowired
    NewUser newUser;
    @Autowired
    ImportUser importUser;

    @RequestMapping("/all")
    public void findAll() {
        List<User> list = userMapper.findAll();
    }

    @RequestMapping("/insert")
    public void insert(User user) {
        userService.insert(user);
    }

    @RequestMapping("/updateByPrimaryKey")
    public void update(User user) {
        userService.updateByPrimaryKey(user);
    }

    @RequestMapping("/insertAndUpdate")
    public void insertAndUpdate(User user) {
        userService.insertAndUpdate(user);
    }

    @RequestMapping("/updateAndInsert")
    public void updateAndInsert(User user) {
        userService.updateAndInsert(user);
    }

    @RequestMapping("/getUserBean")
    public User getUserBean() {
        return userByConfiguretion;
    }

    @RequestMapping("/getNewUser")
    public NewUser getNewUser() {
        newUser.setName("bohan.zhou");
        newUser.setHeight(176);
        return newUser;
    }

    @RequestMapping("/getImportUser")
    public ImportUser getImportUser() {
        importUser.setName("bohan.zhou");
        importUser.setHeight(178);
        return importUser;
    }

}
