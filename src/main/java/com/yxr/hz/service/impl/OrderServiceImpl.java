package com.yxr.hz.service.impl;

import com.yxr.hz.dao.OrderDao;
import com.yxr.hz.dao.RoomDao;
import com.yxr.hz.dao.StudentDao;
import com.yxr.hz.entity.Order;
import com.yxr.hz.entity.Room;
import com.yxr.hz.entity.Student;
import com.yxr.hz.service.OrderService;
import com.yxr.hz.util.OutDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        if(order.getType().equals("退课")){
            order.setMoney(-order.getMoney());
            Student s1=studentDao.findById(order.getSid());
            s1.setState("退课待确认");
            order.setState("退课待确认");
            studentDao.update(s1);
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(new Date());
        order.setDate(now);
        if(order.getType().equals("续费")) {
            Student s = studentDao.findById(order.getSid());
            String date = OutDateUtil.add(s.getOutdate(), order.getCardtype(),s.getDelaytime());
            s.setCardtype(order.getCardtype());
            s.setOutdate(date);
            if(s.getXufei()!=null){
                s.setXufei(s.getXufei()+OutDateUtil.xufei(order.getCardtype()));
                System.out.println(OutDateUtil.xufei(order.getCardtype()));
            }else{
                s.setXufei(OutDateUtil.xufei(order.getCardtype()));
                System.out.println(OutDateUtil.xufei(order.getCardtype()));
            }
            System.out.println(s.getXufei());

            studentDao.update(s);
            order.setState("续费待确认");
        }
        orderDao.insert(order);
    }

    @Override
    public void delete(Integer id) throws ParseException {
        Order order=orderDao.getById(id);
        Student s = studentDao.findById(order.getSid());
        if(order.getState()==null||order.getState().equals("失效")){
            orderDao.delete(order.getId());
            return ;
        }
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
        Collections.sort(list, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return Integer.parseInt(o1.getDate().replace("-",""))-(Integer.parseInt((o2.getDate().replace("-",""))));
            }
        });
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
        List<Order> list=orderDao.getBySId(sid);
        Collections.sort(list, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return Integer.parseInt(o1.getDate().replace("-",""))-(Integer.parseInt((o2.getDate().replace("-",""))));
            }
        });
        return list;
    }

    @Override
    public List<Order> getByRid(Integer rid) {
        List<Order> list=orderDao.getByRId(rid);
        for(Order order:list){
            order.setStudent(studentDao.findById(order.getSid()));
            order.setRoom(roomDao.getById(order.getRid()));
        }

        Collections.sort(list, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return Integer.parseInt(o1.getDate().replace("-",""))-(Integer.parseInt((o2.getDate().replace("-",""))));
            }
        });

        return list;
    }
}
