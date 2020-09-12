package com.yxr.hz.entity;

import lombok.Data;

@Data
public class Admin {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String state;
    private String level;
}
