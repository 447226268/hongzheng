package com.yxr.hz.entity;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private Integer sid;
    private Integer rid;
    private String cardtype;
    private Integer money;
    private String type;
    private String date;
    private String state;
    private String handler;
    private String reason;
    private String way;
    private Student student;
    private Room room;
}
