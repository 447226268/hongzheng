package com.yxr.hz.controller;

import com.yxr.hz.common.CommonResponse;
import com.yxr.hz.common.ResponseUtil;
import com.yxr.hz.entity.Admin;
import com.yxr.hz.entity.BStudent;
import com.yxr.hz.entity.Room;
import com.yxr.hz.entity.Student;
import com.yxr.hz.service.BStudentService;
import com.yxr.hz.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bstudent")
public class BStudentController {
    @Autowired
    private BStudentService bStudentService;
    @Autowired
    private RoomService roomService;
    @GetMapping("/getall")
    public CommonResponse<List<BStudent>> getAll(HttpServletRequest request) throws ParseException {
//        HttpSession session = request.getSession();
//        Admin admin=(Admin)session.getAttribute("admin");
        return ResponseUtil.success(bStudentService.findAll());
    }
    @GetMapping("/getbyid")
    public CommonResponse<BStudent> getById(@RequestParam("id") Integer id) throws ParseException {
//        HttpSession session = request.getSession();
//        Admin admin=(Admin)session.getAttribute("admin");
        return ResponseUtil.success(bStudentService.selectById(id));
    }
    @GetMapping("/number")
    public CommonResponse<Integer> number(@RequestParam("number1") Integer number1,HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();
        Admin admin=(Admin)session.getAttribute("admin");
        List<Room> rooms=roomService.findByAid(admin.getId());
        if(number1==-1){
            List<BStudent> list=new ArrayList<>();
            for(Room room:rooms){
                System.out.println(room.getId());
                List<BStudent> list1=bStudentService.selectByRid(room.getId());
                for(BStudent bStudent:list1){
                    bStudent.setRoom(roomService.findById(bStudent.getRid()));
                }
                if(list1!=null) {
                    list.addAll(list1);
                }
            }
            return ResponseUtil.success(list.size());
        }else{
            return ResponseUtil.success(bStudentService.selectByRid(number1).size());
        }
    }
    @GetMapping("/getRange")
    public CommonResponse<List<BStudent>> getNumber(@RequestParam("number1") Integer number1, @RequestParam("number2") Integer number2, @RequestParam("number3") Integer number3, HttpServletRequest request) throws ParseException {
        List<BStudent> ll=new ArrayList<>();
        HttpSession session = request.getSession();
        Admin admin=(Admin)session.getAttribute("admin");
        List<Room> rooms=roomService.findByAid(admin.getId());
        if(number3==-1){
            for(Room room:rooms){
                if(bStudentService.selectByRid(room.getId())!=null) {
                    List<BStudent> list1=bStudentService.selectByRid(room.getId());
                    for(BStudent bStudent:list1){
                        bStudent.setRoom(roomService.findById(bStudent.getRid()));
                    }
                    ll.addAll(list1);
                }
            }
        }else{
            ll=bStudentService.selectByRid(number3);
            for(BStudent bStudent:ll){
                bStudent.setRoom(roomService.findById(bStudent.getRid()));
            }
        }
        if(number2>ll.size()){
            List<BStudent> list=new ArrayList<>();
            for(int i=number1;i<ll.size();i++){
                list.add(ll.get(i));
            }
            return ResponseUtil.success(list);
        }else{
            List<BStudent> list=new ArrayList<>();
            for(int i=number1;i<number2;i++){
                list.add(ll.get(i));
            }
            return ResponseUtil.success(list);
        }
    }
    @PostMapping("/insert")
    public CommonResponse insert(@RequestBody BStudent student ) throws ParseException {
        bStudentService.insert(student);
        return  ResponseUtil.success("添加成功");
    }
    @GetMapping("/delete")
    public CommonResponse delete(@RequestParam("id") Integer id ) throws ParseException {
        bStudentService.delete(id);
        return  ResponseUtil.success("删除成功");
    }
    @PostMapping("/update")
    public CommonResponse update(@RequestBody BStudent student ) throws ParseException {
        bStudentService.update(student);
        return  ResponseUtil.success("修改成功");
    }
    @GetMapping("/change")
    public CommonResponse<Student> change(@RequestParam("id") Integer id) throws ParseException {
//        HttpSession session = request.getSession();
//        Admin admin=(Admin)session.getAttribute("admin");
        Student student=new Student();
        BStudent bStudent=bStudentService.selectById(id);
        student.setName(bStudent.getName());
        student.setGender(bStudent.getGender());
        student.setBirthday(bStudent.getBirthday());
        student.setTelephone(bStudent.getTelephone());
        student.setInfo("意向学员转正式学员");
        return ResponseUtil.success(student);
    }
    @GetMapping("/listen")
    public CommonResponse listen(@RequestParam("id") Integer id) throws ParseException {
//        HttpSession session = request.getSession();
//        Admin admin=(Admin)session.getAttribute("admin");
        BStudent bStudent=bStudentService.selectById(id);
        bStudent.setIslisten("是");
        bStudentService.update(bStudent);
        return ResponseUtil.success();
    }
    @GetMapping("/addinfo")
    public CommonResponse addinfo(@RequestBody BStudent student) throws ParseException {
//        HttpSession session = request.getSession();
//        Admin admin=(Admin)session.getAttribute("admin");
        BStudent bStudent=bStudentService.selectById(student.getId());
        bStudent.setInfo(student.getInfo());
        bStudentService.update(bStudent);
        return ResponseUtil.success();
    }

}
