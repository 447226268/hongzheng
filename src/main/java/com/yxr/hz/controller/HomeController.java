package com.yxr.hz.controller;

import com.yxr.hz.common.CommonResponse;
import com.yxr.hz.common.ResponseUtil;
import com.yxr.hz.entity.Admin;
import com.yxr.hz.entity.home.Home;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
//    @GetMapping("/getnum")
//    public CommonResponse<Home> getnum(@RequestParam("rid")Integer rid, HttpServletRequest request){
//        HttpSession session = request.getSession();
//        Admin admin=(Admin)session.getAttribute("admin");
//        return ResponseUtil.success();
//    }

}
