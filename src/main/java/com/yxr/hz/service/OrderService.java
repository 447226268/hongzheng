package com.yxr.hz.service;

import com.yxr.hz.entity.Order;

import java.text.ParseException;
import java.util.List;

public interface OrderService {
    void insert(Order order) throws ParseException;
    void delete(Integer id) throws ParseException;
    void update(Order order);
    List<Order> getAll();
    Order getById(Integer id);
    List<Order> getBySId(Integer sid);
    List<Order> getByRid(Integer rid);

}
