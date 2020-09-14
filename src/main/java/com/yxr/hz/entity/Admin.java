package com.yxr.hz.entity;

import lombok.Data;

import java.util.List;

@Data
public class Admin {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String state;
    private Integer level;
    private List<Room> rooms;
}
