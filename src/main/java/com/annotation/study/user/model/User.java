package com.annotation.study.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class User implements Cloneable{
    Integer id;
    String name;
    Integer age;
    Integer sex;

    @Override
    public Object clone()  {
        User user = null;
        try {
            user = (User)super.clone();
        } catch (CloneNotSupportedException e) {
            log.error("User克隆出错：{}", e.getMessage());
        }
        return user;
    }
}
