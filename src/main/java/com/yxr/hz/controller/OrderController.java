package com.yxr.hz.controller;

import com.yxr.hz.common.CommonResponse;
import com.yxr.hz.common.ResponseUtil;
import com.yxr.hz.entity.Order;
import com.yxr.hz.entity.Student;
import com.yxr.hz.service.OrderService;
import com.yxr.hz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/getall")
    public CommonResponse<List<Order>> getAll(){
        return  ResponseUtil.success(orderService.getAll());
    }
    @PostMapping("/insert")
    public CommonResponse insert(@RequestBody Order order ) throws ParseException {
        orderService.insert(order);
        return  ResponseUtil.success();
    }
    @PostMapping("/update")
    public CommonResponse update(@RequestBody Order order ){
        System.out.println(order);
        orderService.update(order);
        return  ResponseUtil.success();
    }

    @GetMapping("/getBySid")
    public CommonResponse<List<Order>> getBySid(@RequestParam("sid") Integer sid){
        return  ResponseUtil.success(orderService.getBySId(sid));
    }
    @GetMapping("/number")
    public CommonResponse<Integer> number() throws ParseException {
        return ResponseUtil.success(orderService.getAll().size());
    }
    @GetMapping("/getRange")
    public CommonResponse<List<Order>> getNumber(@RequestParam("number1") Integer number1, @RequestParam("number2") Integer number2){
       List<Order> ll=orderService.getAll();
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
}
