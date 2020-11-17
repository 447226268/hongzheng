package com.yxr.hz.service.impl;

import com.yxr.hz.dao.OrderDao;
import com.yxr.hz.dao.RoomDao;
import com.yxr.hz.dao.StudentDao;
import com.yxr.hz.entity.Order;
import com.yxr.hz.entity.Room;
import com.yxr.hz.entity.Student;
import com.yxr.hz.service.OrderService;
import com.yxr.hz.service.RoomService;
import com.yxr.hz.service.StudentService;
import com.yxr.hz.util.OutDateUtil;
import com.yxr.hz.util.TimeReverse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private OrderDao orderDao;

    @Override
    public void insert(Student student) throws ParseException {
        student.setState("添加待确认");
        if (student.getCardtype() != null) {
            student.setOutdate(OutDateUtil.add(student.getIndate(), student.getCardtype(),student.getDelaytime()));
            studentDao.insert(student);
            List<Student> list = studentDao.findAll();
            Order order = new Order();
            order.setSid(list.get(list.size() - 1).getId());
            order.setRoom(student.getRoom());
            order.setState("添加待确认");
            order.setMoney(student.getMoney());
            order.setType("开课");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String now = df.format(new Date());
            order.setDate(now);
            order.setCardtype(student.getCardtype());
            order.setRid(student.getRid());
            order.setHandler(student.getHandler());
            order.setWay(student.getWay());
            order.setReason(student.getInfo());
            orderDao.insert(order);

        }
    }

    @Override
    public void update(Student student) throws ParseException {
        student.setState("修改待确认");
        System.out.println();
        student.setOutdate(OutDateUtil.add(student.getIndate(), student.getCardtype(),student.getDelaytime()));
        studentDao.update(student);
    }

    @Override
    public List<Student> findAll() throws ParseException {
        List<Student> list = studentDao.findAll();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(new Date());

        Room room;
        List<Order> list1 = new ArrayList<>();
        for (Student s : list) {
            Integer reday = 0;
            s.setOutdate(OutDateUtil.add(s.getIndate(), s.getCardtype(),s.getDelaytime()));
            list1 = orderDao.getBySId(s.getId());
            Integer money = 0;
            for (Order order : list1) {
                money += order.getMoney();
            }
            if (s.getBirthday() != null) {
                s.setAge((TimeReverse.surplus(s.getBirthday(), now) / 365));
            }
            room = roomDao.getById(s.getRid());
            reday = TimeReverse.surplus(now, s.getOutdate());
            if(s.getXufei()!=null){
                reday+=s.getXufei();
            }
            s.setRoom(room);
            s.setReday(reday);
            s.setMoney(money);
        }
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getReday().compareTo(o2.getReday());
            }
        });
        return list;
    }

    @Override
    public void delete(Integer id) {
        studentDao.delete(id);
    }

    @Override
    public List<Student> selectByName(String name) throws ParseException {
        List<Student> list = studentDao.findByName(name);


        if (list.size() == 1) {
            for (Student s : list) {
                if (s.getState().equals("冻结")) {
                    list.remove(s);
                    return null;
                }
            }
        } else if (list.size() == 0) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(new Date());

        Integer reday = 0;
        Room room;
        List<Order> list1 = new ArrayList<>();
        for (Student s : list) {
            list1 = orderDao.getBySId(s.getId());
            Integer money = 0;
            s.setOutdate(OutDateUtil.add(s.getIndate(), s.getCardtype(),s.getDelaytime()));
            for (Order order : list1) {
                money += order.getMoney();
            }
            if (s.getBirthday() != null) {
                s.setAge((TimeReverse.surplus(s.getBirthday(), now) / 365));
            }
            room = roomDao.getById(s.getRid());
            reday = TimeReverse.surplus(now, s.getOutdate());
            if(s.getXufei()!=null){
                reday+=s.getXufei();
            }
            s.setRoom(room);
            s.setReday(reday);
            s.setMoney(money);
        }
        return list;
    }

    @Override
    public Student selectById(Integer id) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(new Date());
        Student s = studentDao.findById(id);
        if (s.getState().equals("冻结")) {
            return null;
        }
        List<Order> list1 = orderDao.getBySId(s.getId());
        Integer money = 0;
        for (Order order : list1) {
            money += order.getMoney();
        }
        if (s.getBirthday() != null) {
            s.setAge((TimeReverse.surplus(s.getBirthday(), now) / 365));
        }
        Room room = roomDao.getById(s.getRid());
        Integer reday = 0;
        s.setOutdate(OutDateUtil.add(s.getIndate(), s.getCardtype(),s.getDelaytime()));
        reday = TimeReverse.surplus(now, s.getOutdate());
        if(s.getXufei()!=null){
            reday+=s.getXufei();
        }
        s.setRoom(room);
        s.setReday(reday);
        s.setMoney(money);
        return s;
    }

    @Override
    public List<Student> selectByRid(Integer rid, Integer level) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(new Date());
        List<Student> list = studentDao.findByRid(rid);
        List<Student> list1 = new ArrayList<>();
        for (Student s : list) {
            List<Order> list2 = orderDao.getBySId(s.getId());
            Integer money = 0;
            s.setOutdate(OutDateUtil.add(s.getIndate(), s.getCardtype(),s.getDelaytime()));
            Integer reday = 0;
            for (Order order : list2) {
                money += order.getMoney();
            }
            if (s.getBirthday() != null) {
                s.setAge((TimeReverse.surplus(s.getBirthday(), now) / 365));
            }
            reday = TimeReverse.surplus(now, s.getOutdate());
            if(s.getXufei()!=null){
                reday+=s.getXufei();
            }

            Room room = roomDao.getById(s.getRid());
            s.setRoom(room);
            s.setReday(reday);
            s.setMoney(money);

            if (!s.getState().equals("冻结")) {
                list1.add(s);
            }
        }
        if (level == 1) {
            return list;
        } else {
            return list1;
        }
    }

    @Override
    public void updateState(Integer id, Integer level) {
        Student s = studentDao.findById(id);
        if (level == 1) {
            s.setState("失效待确认");
        } else {
            s.setState("删除待确认");
        }
        studentDao.update(s);
    }


}
