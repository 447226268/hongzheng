package com.yxr.hz.entity;

import lombok.Data;

@Data
public class BStudent {
    private Integer id;//编号
    private String name;
    private String gender;//性别
    private String birthday;///生日
    private String telephone;//手机号
    private Integer rid;//咨询校区
    private String state;//跟踪状态
    private String degree;//意向程度
    private String handler;//录入人
    private String date;//录入时间
    private String info;//线索
    private String islisten;//是否试听
    private Room room;
}
