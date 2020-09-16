package com.yxr.hz.controller;

import com.yxr.hz.common.CommonResponse;
import com.yxr.hz.common.ResponseUtil;
import com.yxr.hz.entity.Admin;
import com.yxr.hz.entity.Order;
import com.yxr.hz.entity.Student;
import com.yxr.hz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        studentService.insert(student);
        return  ResponseUtil.success();
    }
    @PostMapping("/update")
    public CommonResponse update(@RequestBody Student student ) {
        student.setState("修改待确认");
        studentService.update(student);
        return  ResponseUtil.success("修改成功");
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
    public CommonResponse<Integer> number(@RequestParam("rid") Integer rid,HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();
        Admin admin=(Admin)session.getAttribute("admin");
        return ResponseUtil.success(studentService.selectByRid(rid,admin.getLevel()).size());
    }
    @GetMapping("/getRange")
    public CommonResponse<List<Student>> getNumber(@RequestParam("number1") Integer number1, @RequestParam("number2") Integer number2, @RequestParam("number3") Integer number3,HttpServletRequest request) throws ParseException {
//        List<Student> ll=studentService.findAll();
        HttpSession session = request.getSession();
        Admin admin=(Admin)session.getAttribute("admin");
        List<Student> ll=studentService.selectByRid(number3,admin.getLevel());
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
    @GetMapping("/delete")
    public CommonResponse delete(@RequestParam("id") Integer id, HttpServletRequest request){
        HttpSession session = request.getSession();
        Admin admin=(Admin)session.getAttribute("admin");
        if(admin==null||admin.getLevel()==null){
            return ResponseUtil.error("请登陆");
        }
        Integer level=admin.getLevel();
        studentService.updateState(id,level);
        return ResponseUtil.success("删除成功");
    }

}
