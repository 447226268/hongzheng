package com.yxr.hz.service;

import com.yxr.hz.entity.Student;

import java.text.ParseException;
import java.util.List;

public interface StudentService {
    void insert(Student student) throws ParseException;
    void update(Student student);
    List<Student> findAll() throws ParseException;
    void delete(Integer id);
    List<Student> selectByName(String name) throws ParseException;
    Student selectById(Integer id) throws ParseException;
    List<Student> selectByRid(Integer rid,Integer level) throws ParseException;
    void updateState(Integer id,Integer level);





}
