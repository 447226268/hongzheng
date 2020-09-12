package com.yxr.hz.service.impl;

import com.yxr.hz.dao.OrderDao;
import com.yxr.hz.entity.Order;
import com.yxr.hz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public void insert(Order order) {
        orderDao.insert(order);
    }

    @Override
    public void delete(Integer id) {
        orderDao.delete(id);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order getById(Integer id) {
        return orderDao.getById(id);
    }

    @Override
    public List<Order> getBySId(Integer sid) {
        return orderDao.getBySId(sid);
    }
}
