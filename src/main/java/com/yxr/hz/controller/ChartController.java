package com.yxr.hz.controller;

import com.yxr.hz.common.CommonResponse;
import com.yxr.hz.common.ResponseUtil;
import com.yxr.hz.entity.Order;
import com.yxr.hz.entity.Student;
import com.yxr.hz.entity.home.Haha;
import com.yxr.hz.entity.home.Haha1;
import com.yxr.hz.service.OrderService;
import com.yxr.hz.service.RoomService;
import com.yxr.hz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chart")
public class ChartController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private OrderService orderService;
    @GetMapping("/getall")
    public CommonResponse<List<Haha1>> chart(@RequestParam("rid") Integer rid, @RequestParam("year") Integer year , @RequestParam("month") Integer month) throws ParseException {
        List<Student> student=studentService.selectByRid(rid,0);
        List<Order> orders=orderService.getByRid(rid);
        List<Haha1> list=new ArrayList<>();
        for(int i=1;i<=month;i++){
            Haha1 haha1=new Haha1();
            haha1.setDay(i+"月");
            haha1.setPeople(0);
            haha1.setMoney(0);
            for(Student  s:student){
                if(Integer.parseInt(s.getIndate().split("-")[0])==year&&Integer.parseInt(s.getIndate().split("-")[1])==i){
                    haha1.setPeople(haha1.getPeople()+1);
                }
            }
            for(Order  o:orders){

                if(Integer.parseInt(o.getDate().split("-")[0])==year&&Integer.parseInt(o.getDate().split("-")[1])==i){
                    if(o.getMoney()!=null) {
                        haha1.setMoney(haha1.getMoney() + o.getMoney());
                    }
                }
            }

            list.add(haha1);
        }

        return ResponseUtil.success(list);
    }


}
