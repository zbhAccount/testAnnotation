package com.annotation.study.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportUser {
    Integer id;
    String name;
    Integer age;
    Integer sex;
    Integer height;
    Integer weight;
}
