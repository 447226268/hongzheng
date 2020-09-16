package com.yxr.hz.dao;

import com.yxr.hz.entity.BStudent;
import com.yxr.hz.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BStudentDao {
    List<BStudent> findAll();
    BStudent findById(Integer id);
    List<BStudent> findByName(String name);
    void insert(BStudent student);
    void delete(Integer id);
    void update(BStudent student);
    List<BStudent> findByRid(Integer rid);
}
