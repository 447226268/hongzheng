package com.yxr.hz.service;

import com.yxr.hz.entity.Order;

import java.util.List;

public interface OrderService {
    void insert(Order order);
    void delete(Integer id);
    void update(Order order);
    List<Order> getAll();
    Order getById(Integer id);
    List<Order> getBySId(Integer sid);

}
