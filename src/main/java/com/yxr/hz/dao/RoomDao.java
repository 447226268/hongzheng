package com.yxr.hz.dao;

import com.yxr.hz.entity.Room;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomDao {
    void insert(Room room);
    List<Room> getAll();
    List<Room> getByAId(Integer aid);
    Room getById(Integer id);
    void delete(Integer id);
    void update(Room room);
}
