package com.yxr.hz.entity.home;

import com.yxr.hz.entity.Student;
import lombok.Data;

import java.util.List;
@Data
public class Home {
    private List<Student> birthdayStudent;
    private Integer numberOfAddStudents;
    private Integer numberOfStudents;
    private Integer numberOfAddBStudents;
    private Integer birthdayThisMonth;
    private Integer xufei;
    private Integer newStudentsAddedToday;
    private Integer daixufei;
    private Integer zhuxiao;
    private List<Haha> studentlist;
//    private Haha[] monthlist;
}
