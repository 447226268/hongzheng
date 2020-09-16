package com.yxr.hz.entity.home;

import com.yxr.hz.entity.Student;
import lombok.Data;

import java.util.List;
@Data
public class Home {
    private List<Student> birthdayStudent;
    private Integer numberOfStudents;
    private Integer birthdayThisMonth;
    private Integer graduate;
    private Integer newStudentsAddedToday;
}
