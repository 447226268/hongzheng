package com.yxr.hz.service;

import com.yxr.hz.entity.Admin;

import java.util.List;

public interface AdminService {
    void insert(Admin admin);
    void update(Admin admin);
    void delete(Integer id);
    Admin findById(Integer id);
    Admin findByUsername(String username);
    List<Admin> findAll();
}
