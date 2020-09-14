package com.yxr.hz.entity;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private Integer id;
    private String coach;//教练
    private String image;
    private String name;
    private String gender;
    private Integer age;
    private String birthday;
    private String telephone;
    private String pname;
    private String phone;
    private String relation;//关系
    private String level;
    private String state;
    private String indate;
    private String outdate;
    private Integer reday;//剩余天数
    private Integer num;//上课次数
    private Integer rid;
    private Integer delaytime;//延期天数
    private String handler;//录入人
    private String info;
    private Room room;
    private List<Order> orders;
    private Admin admin;
}
