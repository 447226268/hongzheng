package com.yxr.hz.entity;

import lombok.Data;

@Data
public class Room {
    private Integer id;
    private String name;
    private Integer aid;
    private String state;
    private Admin admin;
}
