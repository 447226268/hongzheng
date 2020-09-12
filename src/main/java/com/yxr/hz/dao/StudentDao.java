package com.yxr.hz.dao;

import com.yxr.hz.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {
    List<Student> findAll();
    Student findById(Integer id);
    List<Student> findByName(String name);
    void insert(Student student);
    void delete(Integer id);
    void update(Student student);
}
