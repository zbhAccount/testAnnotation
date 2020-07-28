package com.annotation.study.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class NewUser {
    Integer id;
    String name;
    Integer age;
    Integer sex;
    Integer height;
}
