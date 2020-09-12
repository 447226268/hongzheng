package com.yxr.hz.service;

import com.yxr.hz.entity.Student;

import java.util.List;

public interface StudentService {
    void insert(Student student);
    void update(Student student);
    List<Student> findAll();
    void delete(Integer id);
    List<Student> selectByName(String name);
    Student selectById(Integer id);
}
