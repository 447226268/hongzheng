package com.yxr.hz.controller;

import com.yxr.hz.common.CommonResponse;
import com.yxr.hz.common.ResponseUtil;
import com.yxr.hz.entity.Admin;
import com.yxr.hz.entity.Order;
import com.yxr.hz.entity.Room;
import com.yxr.hz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/getall")
    public CommonResponse<List<Admin>> getAll( HttpServletRequest request){
        HttpSession session = request.getSession();
        Admin admin=(Admin)session.getAttribute("admin");
        return ResponseUtil.success(adminService.findAll());
    }
    @GetMapping("/delete")
    public CommonResponse delete( @RequestParam("id") Integer id){
        adminService.delete(id);
        return ResponseUtil.success("删除成功");
    }
    @PostMapping("/insert")
    public CommonResponse insert(@RequestBody Admin admin ){
        System.out.println(admin);
        admin.setState("正常");
        adminService.insert(admin);
        return  ResponseUtil.success();
    }
    @PostMapping("/update")
    public CommonResponse update(@RequestBody Admin admin ){
        System.out.println(admin);
        admin.setState("正常");
        adminService.update(admin);
        return  ResponseUtil.success();
    }
    @GetMapping("/updatestate")
    public CommonResponse updatestate(@RequestParam("id") Integer id){
        Admin admin=adminService.findById(id);
        System.out.println(admin);
        if(admin.getState().equals("正常")){
            admin.setState("冻结");
        }else{
            admin.setState("正常");
        }
        System.out.println(admin);
        adminService.update(admin);
        return  ResponseUtil.success();
    }
    @PostMapping("/login")
    public CommonResponse login(@RequestBody Admin admin , HttpServletRequest request){
        System.out.println(admin);
        HttpSession session = request.getSession();
        Admin admin1=adminService.findByUsername(admin.getUsername());
        if(admin1!=null){
            if(admin.getPassword().equals(admin1.getPassword())){
                if(admin1.getState().equals("冻结")){
                    return ResponseUtil.error("用户被冻结，请联系管理员");
                }else{
                    session.setAttribute("admin",admin1);
                    return ResponseUtil.success("登陆成功");
                }
            }else{
                return ResponseUtil.error("用户名或者密码错误");
            }
        }
        return ResponseUtil.error("用户名或者密码错误");
    }
    @GetMapping("/layout")
    public CommonResponse layout(HttpServletRequest request){
        HttpSession session = request.getSession();
        Admin admin=(Admin)session.getAttribute("admin");
        session.removeAttribute("admin");
        if(admin==null){
            ResponseUtil.error("用户已过期");
        }
        return ResponseUtil.error(admin.getUsername()+"退出成功");
    }
    @GetMapping("/state")
    public CommonResponse state(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return ResponseUtil.auth_error("登陆过期，重新登陆");
        } else {
            return ResponseUtil.auth_success("用户信息正常");
        }
    }
}
