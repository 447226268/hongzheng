package com.yxr.hz.dao;

import com.yxr.hz.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao {
    void insert(Admin admin);
    void update(Admin admin);
    void delete(Integer id);
    List<Admin> findAll();
    Admin findById(Integer id);
    Admin findByUsername(String username);
}
