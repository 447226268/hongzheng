package com.yxr.hz.service.impl;

import com.yxr.hz.dao.StudentDao;
import com.yxr.hz.entity.Student;
import com.yxr.hz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Override
    public void insert(Student student) {
        studentDao.insert(student);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public void delete(Integer id) {
        studentDao.delete(id);
    }

    @Override
    public List<Student> selectByName(String name) {
        return studentDao.findByName(name);
    }

    @Override
    public Student selectById(Integer id) {
        return studentDao.findById(id);
    }
}
