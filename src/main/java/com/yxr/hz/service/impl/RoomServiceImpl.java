package com.yxr.hz.service.impl;

import com.yxr.hz.dao.AdminDao;
import com.yxr.hz.dao.RoomDao;
import com.yxr.hz.entity.Admin;
import com.yxr.hz.entity.Room;
import com.yxr.hz.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private AdminDao adminDao;
    @Override
    public void insert(Room room) {
        roomDao.insert(room);
    }

    @Override
    public void delete(Integer id) {
        roomDao.delete(id);
    }

    @Override
    public List<Room> findByAid(Integer aid) {
        return roomDao.getByAId(aid);
    }

    @Override
    public Room findById(Integer id) {
        return roomDao.getById(id);
    }

    @Override
    public List<Room> findAll() {
        List<Room> list=roomDao.getAll();
        for(Room room:list){
            Admin admin=adminDao.findById(room.getAid());
            room.setAdmin(admin);
        }
        return list;
    }

    @Override
    public void update(Room room) {
        roomDao.update(room);
    }
}
