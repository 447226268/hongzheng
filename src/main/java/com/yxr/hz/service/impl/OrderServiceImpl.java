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
            Student s1=studentDao.findById(order.getSid());
            s1.setState("退费待确认");
            order.setState("续费待确认");
            studentDao.update(s1);
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(new Date());
        order.setDate(now);
        if(order.getType().equals("续费")) {
            Student s = studentDao.findById(order.getSid());
            String date = OutDateUtil.add(s.getOutdate(), order.getCardtype());
            s.setCardtype(order.getCardtype());
            s.setOutdate(date);
            studentDao.update(s);
            order.setState("续费待确认");
        }
        orderDao.insert(order);

    }

    @Override
    public void delete(Integer id) throws ParseException {
        Order order=orderDao.getById(id);
        Student s = studentDao.findById(order.getSid());
        if(order.getType().equals("续费")){
            String date = OutDateUtil.back(s.getOutdate(), order.getCardtype());
            s.setCardtype(order.getCardtype());
            s.setOutdate(date);
            studentDao.update(s);
        }
        order.setState("失效");
        orderDao.update(order);
    }

    @Override
    public void update(Order order) {
        order.setState("修改待确认");
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
        Order o=orderDao.getById(id);
        o.setStudent(studentDao.findById(o.getSid()));
        o.setRoom(roomDao.getById(o.getRid()));
        return o;
    }

    @Override
    public List<Order> getBySId(Integer sid) {
        return orderDao.getBySId(sid);
    }

    @Override
    public List<Order> getByRid(Integer rid) {
        List<Order> list=orderDao.getByRId(rid);
        for(Order order:list){
            order.setStudent(studentDao.findById(order.getSid()));
            order.setRoom(roomDao.getById(order.getRid()));
        }
        return list;
    }
}
