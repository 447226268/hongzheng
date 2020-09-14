package com.yxr.hz.controller;

import com.yxr.hz.common.CommonResponse;
import com.yxr.hz.common.ResponseUtil;
import com.yxr.hz.entity.Order;
import com.yxr.hz.entity.Student;
import com.yxr.hz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/getall")
    public CommonResponse<List<Student>> getAll() throws ParseException {
        return  ResponseUtil.success(studentService.findAll());
    }
    @PostMapping("/insert")
    public CommonResponse insert(@RequestBody Student student ) throws ParseException {
        System.out.println(student);
        studentService.insert(student);
        return  ResponseUtil.success();
    }
    @GetMapping("/getbyname")
    public CommonResponse<List<Student>> getByName(@RequestParam("name") String name) throws ParseException {
        return  ResponseUtil.success(studentService.selectByName(name));
    }
    @GetMapping("/getbyid")
    public CommonResponse<Student> getById(@RequestParam("id") Integer id) throws ParseException {
        return  ResponseUtil.success(studentService.selectById(id));
    }
    @GetMapping("/number")
    public CommonResponse<Integer> number() throws ParseException {
        return ResponseUtil.success(studentService.findAll().size());
    }
    @GetMapping("/getRange")
    public CommonResponse<List<Student>> getNumber(@RequestParam("number1") Integer number1, @RequestParam("number2") Integer number2) throws ParseException {
        List<Student> ll=studentService.findAll();
        if(number2>ll.size()){
            List<Student> list=new ArrayList<>();
            for(int i=number1;i<ll.size();i++){
                list.add(ll.get(i));
            }
            return ResponseUtil.success(list);
        }else{
            List<Student> list=new ArrayList<>();
            for(int i=number1;i<number2;i++){
                list.add(ll.get(i));
            }
            return ResponseUtil.success(list);
        }
    }
}
