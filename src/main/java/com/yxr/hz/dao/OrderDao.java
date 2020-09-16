package com.yxr.hz.dao;

import com.yxr.hz.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    void insert(Order order);
    List<Order> getAll();
    List<Order> getBySId(Integer sid);
    Order getById(Integer id);
    void delete(Integer id);
    void update(Order order);
    List<Order> getByRId(Integer rid);
}
