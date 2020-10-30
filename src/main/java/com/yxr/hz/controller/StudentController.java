package com.yxr.hz.controller;

import com.yxr.hz.common.CommonResponse;
import com.yxr.hz.common.ResponseUtil;
import com.yxr.hz.dao.StudentDao;
import com.yxr.hz.entity.Admin;
import com.yxr.hz.entity.BStudent;
import com.yxr.hz.entity.Order;
import com.yxr.hz.entity.Student;
import com.yxr.hz.service.BStudentService;
import com.yxr.hz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private BStudentService bStudentService;
    @Autowired
    private StudentDao studentDao;

    @GetMapping("/getall")
    public CommonResponse<List<Student>> getAll() throws ParseException {
        return ResponseUtil.success(studentService.findAll());
    }

    @PostMapping("/insert")
    public CommonResponse insert(@RequestBody Student student) throws ParseException {
        studentService.insert(student);
        if (student.getId() != null) {
            bStudentService.delete(student.getId());
        }
        return ResponseUtil.success();
    }

    @PostMapping("/update")
    public CommonResponse update(@RequestBody Student student) throws ParseException {
        studentService.update(student);
        return ResponseUtil.success("修改成功");
    }

    @GetMapping("/getbyname")
    public CommonResponse<List<Student>> getByName(@RequestParam("name") String name) throws ParseException {
        return ResponseUtil.success(studentService.selectByName(name));
    }

    @GetMapping("/getbyid")
    public CommonResponse<Student> getById(@RequestParam("id") Integer id) throws ParseException {
        return ResponseUtil.success(studentService.selectById(id));
    }

    @GetMapping("/number")
    public CommonResponse<Integer> number(@RequestParam("rid") Integer rid,@RequestParam("state") String state, HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        System.out.println(state);
        List<Student> list = studentService.selectByRid(rid, 0);
        List<Student> list1 = new ArrayList<>();
        if(state.equals("全部")){
            list1=list;
        }else{
            for (Student student : list) {
                if (student.getState().equals(state)) {
                    list1.add(student);
                }
            }
        }
        return ResponseUtil.success(list1.size());
    }

    @GetMapping("/getRange")
    public CommonResponse<List<Student>> getNumber(@RequestParam("number1") Integer number1, @RequestParam("number2") Integer number2, @RequestParam("number3") Integer number3, HttpServletRequest request) throws ParseException {
//        List<Student> ll=studentService.findAll();
        HttpSession session = request.getSession();

        Admin admin = (Admin) session.getAttribute("admin");
        List<Student> ll = studentService.selectByRid(number3, admin.getLevel());
        Collections.sort(ll, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getReday().compareTo(o2.getReday());
            }
        });
        if (number2 > ll.size()) {
            List<Student> list = new ArrayList<>();
            for (int i = number1; i < ll.size(); i++) {
                list.add(ll.get(i));
            }
            return ResponseUtil.success(list);
        } else {
            List<Student> list = new ArrayList<>();
            for (int i = number1; i < number2; i++) {
                list.add(ll.get(i));
            }

            Collections.sort(list, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o1.getReday().compareTo(o2.getReday());
                }
            });
            return ResponseUtil.success(list);
        }
    }

    @GetMapping("/delete")
    public CommonResponse delete(@RequestParam("id") Integer id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null || admin.getLevel() == null) {
            return ResponseUtil.error("请登陆");
        }
        Integer level = admin.getLevel();
        studentService.updateState(id, level);
        return ResponseUtil.success("删除成功");
    }

    @GetMapping("/queren")
    public CommonResponse queren(@RequestParam("id") Integer id) throws ParseException {
        Student student = studentService.selectById(id);
        if (student.getState().equals("失效待确认") || student.getState().equals("失效")) {
            student.setState("失效");
        } else {
            student.setState("正常");
        }
        studentDao.update(student);
        return ResponseUtil.success("已确认");
    }

    @GetMapping("/statesearch")
    public CommonResponse<List<Student>> stateSearch(@RequestParam("state") String state, @RequestParam("number1") Integer number1, @RequestParam("number2") Integer number2, @RequestParam("number3") Integer number3) throws ParseException {
        System.out.println(state);
        List<Student> list = studentService.selectByRid(number3, 0);
        List<Student> list1 = new ArrayList<>();
        for (Student student : list) {
            if (student.getState().equals(state)) {
                list1.add(student);
            }
        }
        Collections.sort(list1, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getReday().compareTo(o2.getReday());
            }
        });
        List<Student> list2 = new ArrayList<>();
        System.out.println(number2);
        System.out.println(list1.size());
        if (number2 > list1.size()) {
            for (int i = number1; i < list1.size(); i++) {
                list2.add(list1.get(i));
            }
            System.out.println(list2.size());
            return ResponseUtil.success(list2);
        } else {
            for (int i = number1; i < number2; i++) {
                list2.add(list1.get(i));
            }
            System.out.println(list2.size());
        }
        return ResponseUtil.success(list2);
    }

    @GetMapping("/statesearchnum")
    public CommonResponse<Integer> statesearchnum(@RequestParam("state") String state, @RequestParam("rid") Integer rid) throws ParseException {
        System.out.println(state);
        List<Student> list = studentService.selectByRid(rid, 0);
        List<Student> list1 = new ArrayList<>();
        for (Student student : list) {
            if (student.getState().equals(state)) {
                list1.add(student);
            }
        }
        return ResponseUtil.success(list1.size());
    }
}
