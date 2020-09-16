package com.yxr.hz.controller;

import com.yxr.hz.common.CommonResponse;
import com.yxr.hz.common.ResponseUtil;
import com.yxr.hz.dao.OrderDao;
import com.yxr.hz.entity.Admin;
import com.yxr.hz.entity.Order;
import com.yxr.hz.entity.Room;
import com.yxr.hz.entity.Student;
import com.yxr.hz.service.OrderService;
import com.yxr.hz.service.RoomService;
import com.yxr.hz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private OrderDao orderDao;
    @GetMapping("/getall")
    public CommonResponse<List<Order>> getAll(HttpServletRequest request){
        HttpSession session = request.getSession();
        Admin admin=(Admin)session.getAttribute("admin");
        List<Order> list=new ArrayList<>();
        if(admin.getLevel()==1){
            return  ResponseUtil.success(orderService.getAll());
        }else{
            List<Room> rooms=roomService.findByAid(admin.getId());
            for(Room room:rooms){
                list.addAll(orderService.getByRid(room.getId()));
            }
        }
        return null;
    }
    @PostMapping("/insert")
    public CommonResponse insert(@RequestBody Order order ) throws ParseException {
        orderService.insert(order);
//        Student s=studentService.selectById(order.getSid());

//        s.setOutdate();
//        studentService.update();
        return  ResponseUtil.success("订单插入成功");
    }
    @PostMapping("/update")
    public CommonResponse update(@RequestBody Order order ){
        System.out.println(order);
        orderService.update(order);
        return  ResponseUtil.success();
    }
    @GetMapping("/getbyid")
    public CommonResponse<Order> getById(@RequestParam("id") Integer id){
        return  ResponseUtil.success(orderService.getById(id));
    }
    @GetMapping("/getBySid")
    public CommonResponse<List<Order>> getBySid(@RequestParam("sid") Integer sid){
        return  ResponseUtil.success(orderService.getBySId(sid));
    }
    @GetMapping("/number")
    public CommonResponse<Integer> number() throws ParseException {
        return ResponseUtil.success(orderService.getAll().size());
    }
    @GetMapping("/delete")
    public CommonResponse delete(@RequestParam("id") Integer id) throws ParseException {
        orderService.delete(id);
        return ResponseUtil.success("删除成功");
    }
    @GetMapping("/getRange")
    public CommonResponse<List<Order>> getNumber(@RequestParam("number1") Integer number1, @RequestParam("number2") Integer number2,HttpServletRequest request){
        List<Order> ll=new ArrayList<>();
        HttpSession session = request.getSession();
        Admin admin=(Admin)session.getAttribute("admin");
        if(admin.getLevel()==1){
            ll=orderService.getAll();
        }else{
            List<Room> list=roomService.findByAid(admin.getId());
            for(Room room:list){
                ll.addAll(orderService.getByRid(room.getId()));
            }
        }
        if(number2>ll.size()){
            List<Order> list=new ArrayList<>();
            for(int i=number1;i<ll.size();i++){
                list.add(ll.get(i));
            }
            return ResponseUtil.success(list);
        }else{
            List<Order> list=new ArrayList<>();
            for(int i=number1;i<number2;i++){
                list.add(ll.get(i));
            }
            return ResponseUtil.success(list);
        }
    }
    @GetMapping("/updatestate")
    public CommonResponse updatestate(@RequestParam("id") Integer id){
        Order room=orderService.getById(id);
        if(room.getState().equals("失效")||room.getState().equals("冻结")){
            return  ResponseUtil.success("修改成功");
        }
        room.setState("正常");
        orderDao.update(room);
        return  ResponseUtil.success("修改成功");
    }
}
