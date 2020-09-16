package com.yxr.hz.service;

import com.yxr.hz.entity.BStudent;
import com.yxr.hz.entity.Student;

import java.text.ParseException;
import java.util.List;

public interface BStudentService {
    void insert(BStudent student) throws ParseException;
    void update(BStudent student);
    List<BStudent> findAll() throws ParseException;
    void delete(Integer id);
    List<BStudent> selectByName(String name) throws ParseException;
    BStudent selectById(Integer id) throws ParseException;
    List<BStudent> selectByRid(Integer rid);
    void updateState(Integer id);





}
