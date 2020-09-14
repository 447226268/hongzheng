package com.yxr.hz.service.impl;

import com.yxr.hz.dao.AdminDao;
import com.yxr.hz.dao.RoomDao;
import com.yxr.hz.entity.Admin;
import com.yxr.hz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private RoomDao roomDao;
    @Override
    public void insert(Admin admin) {
        admin.setLevel(0);
        adminDao.insert(admin);
    }

    @Override
    public void update(Admin admin) {
        Admin a=adminDao.findById(admin.getId());
        admin.setLevel(a.getLevel());
        adminDao.update(admin);
    }

    @Override
    public void delete(Integer id) {
        adminDao.delete(id);
    }

    @Override
    public Admin findById(Integer id) {
        return adminDao.findById(id);
    }

    @Override
    public Admin findByUsername(String username) {
        return adminDao.findByUsername(username);
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> list=adminDao.findAll();
        for(Admin admin:list){
            admin.setRooms(roomDao.getByAId(admin.getId()));
        }
        return list;
    }
}
