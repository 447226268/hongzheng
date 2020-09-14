package com.yxr.hz.service.impl;

import com.yxr.hz.dao.OrderDao;
import com.yxr.hz.dao.RoomDao;
import com.yxr.hz.dao.StudentDao;
import com.yxr.hz.entity.Order;
import com.yxr.hz.entity.Student;
import com.yxr.hz.service.OrderService;
import com.yxr.hz.util.OutDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private RoomDao roomDao;

    @Override
    public void insert(Order order) throws ParseException {
        if(order.getType().equals("退费")){
            order.setMoney(-order.getMoney());
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(new Date());
        order.setDate(now);
        orderDao.insert(order);
        if(order.getType().equals("续费")) {
            Student s = studentDao.findById(order.getSid());
            String date = OutDateUtil.add(s.getOutdate(), order.getCardtype());
            s.setOutdate(date);
            studentDao.update(s);
        }else{
            Student s = studentDao.findById(order.getSid());
            s.setOutdate(OutDateUtil.add(s.getIndate(),s.getCardtype()));
            s.setCardtype(order.getCardtype());
            studentDao.update(s);
        }
    }

    @Override
    public void delete(Integer id) throws ParseException {
        Order order=orderDao.getById(id);
        order.setState("冻结");
        orderDao.update(order);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public List<Order> getAll() {
        List<Order> list=orderDao.getAll();
        for(Order order:list){
            order.setStudent(studentDao.findById(order.getSid()));

            order.setRoom(roomDao.getById(order.getRid()));
        }
        return list;
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
