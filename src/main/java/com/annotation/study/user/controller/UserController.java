package com.annotation.study.user.controller;


import com.annotation.study.user.mapper.UserMapper;
import com.annotation.study.user.model.ImportUser;
import com.annotation.study.user.model.NewUser;
import com.annotation.study.user.model.User;
import com.annotation.study.user.service.IGirlService;
import com.annotation.study.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Stack;

@Slf4j
@RestController
@RequestMapping("/user")
@Import({ImportUser.class})
@PropertySource(value = "classpath:config/config.properties", encoding = "utf-8")
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
    @Autowired
    IGirlService iGirlService;

    @Value("${name}")
    String name;
    @Value("${age}")
    Integer age;
    @Value("${sex}")
    Integer sex;

    @RequestMapping("/all")
    public List<User> findAll() {
        return userMapper.findAll();
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

    @RequestMapping("/getValueUser")
    public String getValueUser(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("name:");
        stringBuilder.append(name);

        stringBuilder.append(" age:");
        stringBuilder.append(age);

        stringBuilder.append(" sex:");
        stringBuilder.append(sex);
        Stack st = new Stack();

        return stringBuilder.toString();
    }

    @RequestMapping("insertUser")
    public void insertUser(){
        User user = new User();
        user.setSex(1);
        user.setName("蜜姐姐");
        user.setAge(27);
        iGirlService.insert(user);
    }

    @RequestMapping("updateUser")
    public void updateUser(){
        Thread t = Thread.currentThread();
        log.info("多线程：{}", t.getName());
        iGirlService.update(new User());
    }

    @RequestMapping("asyncUser")
    public void asyncUser(){
        Thread t = Thread.currentThread();
        log.info("多线程：{}", t.getName());
        iGirlService.async(new User());
    }

    @RequestMapping("futureUser")
    public void futureUser(){
        iGirlService.execute();
    }
}
