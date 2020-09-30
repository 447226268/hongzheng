package com.yxr.hz.controller;

import com.yxr.hz.common.CommonResponse;
import com.yxr.hz.common.ResponseUtil;
import com.yxr.hz.entity.*;
import com.yxr.hz.entity.home.Haha;
import com.yxr.hz.entity.home.Home;
import com.yxr.hz.service.BStudentService;
import com.yxr.hz.service.OrderService;
import com.yxr.hz.service.RoomService;
import com.yxr.hz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BStudentService bStudentService;
    @GetMapping("/table1")
    public CommonResponse<Home> table1(@RequestParam("rid") Integer rid) throws ParseException {
        Room room=roomService.findById(rid);
        Home home=new Home();
        List<Order> list=orderService.getByRid(rid);
        Integer numberOfStudents=0;
        Integer zhuxiao=0;
        List<Student> birthdayStudent=new ArrayList<>();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        System.out.println(date);
        Integer xufei=0;
        Integer numberOfAddStudents=0;
        Integer numberOfAddBStudents=0;
        Integer tuifei=0;
        Integer birthdayThisMonth=0;
        Integer kuaiguoqi=0;
        for(Order o:list){
            if(o.getDate().equals(date)){
                if(o.getType().equals("开课")){
                    numberOfAddStudents++;
                }else if(o.getType().equals("续费")){
                    xufei++;
                }else{
                    tuifei++;
                }
            }
        }

        List<BStudent> list1=bStudentService.findAll();
        for(BStudent bStudent:list1){
            if(bStudent.getDate().equals(date)){
                numberOfAddBStudents++;
            }
        }
        List<Haha> hahas=new ArrayList<>();
        List<Student> list2=studentService.selectByRid(rid,1);
        Haha haha=new Haha();
        haha.setName("意向学员转正式学员");
        haha.setNum(0);
        Haha haha1=new Haha();
        haha1.setName("现场报名");
        haha1.setNum(0);
        Haha haha2=new Haha();
        haha2.setName("毕业");
        haha2.setNum(0);
        for(Student student:list2){
            if(student.getState().equals("正常")){
                numberOfStudents++;
            }
            if(student.getState().equals("失效")){
                zhuxiao++;
            }
            if(student.getReday()<10){
                kuaiguoqi++;
            }

            if(student.getBirthday().split("-")[1].equals(date.split("-")[1])&&student.getBirthday().split("-")[2].equals(date.split("-")[2])){
                System.out.println(student);
                birthdayStudent.add(student);
            }
            if(student.getBirthday().split("-")[1].equals(date.split("-")[1])){
                birthdayThisMonth++;
            }
            if(student.getState().equals("失效")){
                haha2.setNum(haha2.getNum()+1);
            }else if(student.getInfo().contains("意向学员转正式学员")){
                haha.setNum(haha.getNum()+1);
            }else if(student.getType().equals("购买课程")){
                haha1.setNum(haha1.getNum()+1);
            }else {

            }
        }
        hahas.add(haha);
        hahas.add(haha1);
        hahas.add(haha2);
        home.setNumberOfAddStudents(numberOfAddStudents);
        home.setNumberOfAddBStudents(numberOfAddBStudents);
        home.setXufei(xufei);
        home.setZhuxiao(zhuxiao);
        home.setStudentlist(hahas);
        home.setDaixufei(kuaiguoqi);
        home.setBirthdayThisMonth(birthdayThisMonth);
        home.setNumberOfStudents(numberOfStudents);
        System.out.println(birthdayStudent);
        home.setBirthdayStudent(birthdayStudent);
        return ResponseUtil.success(home);

    }

}
