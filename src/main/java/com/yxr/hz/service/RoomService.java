package com.yxr.hz.service;

import com.yxr.hz.entity.Room;

import java.util.List;

public interface RoomService {
    void insert(Room room);
    void delete(Integer id);
    List<Room> findByAid(Integer aid);
    Room findById(Integer id);
    List<Room> findAll();
    void update(Room room);




}
