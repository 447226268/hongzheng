package com.yxr.hz.entity.home;

import com.yxr.hz.entity.Student;
import lombok.Data;

import java.util.List;
@Data
public class Home {
    private Integer birthday;
    private List<Student> birthdayStudent;
    private Integer stuSum;
    private Integer inSchool;
    private Integer graduate;
    private Integer newStudent;
}
