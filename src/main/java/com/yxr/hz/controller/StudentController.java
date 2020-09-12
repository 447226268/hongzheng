package com.yxr.hz.controller;

import com.yxr.hz.common.CommonResponse;
import com.yxr.hz.common.ResponseUtil;
import com.yxr.hz.entity.Student;
import com.yxr.hz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/getall")
    public CommonResponse<List<Student>> getAll(){
        return  ResponseUtil.success(studentService.findAll());
    }
    @PostMapping("/insert")
    public CommonResponse insert(@RequestBody Student student ){
        studentService.insert(student);
        return  ResponseUtil.success();
    }
}
