package com.annotation.study.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    Integer id;
    String name;
    Integer age;
    Integer sex;
}
