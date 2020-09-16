package com.yxr.hz.controller;

import com.yxr.hz.common.CommonResponse;
import com.yxr.hz.common.ResponseUtil;
import com.yxr.hz.entity.Admin;
import com.yxr.hz.entity.Order;
import com.yxr.hz.entity.Room;
import com.yxr.hz.service.OrderService;
import com.yxr.hz.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @GetMapping("/getall")
    public CommonResponse<List<Room>> getAll(HttpServletRequest request){
        HttpSession session = request.getSession();
        Admin admin=(Admin)session.getAttribute("admin");
        if(admin==null){
            return ResponseUtil.error("用户过期，重新登陆");
        }
        if(admin.getLevel()==1){
            return ResponseUtil.success(roomService.findAll());
        }else{
            return ResponseUtil.success(roomService.findByAid(admin.getId()));
        }
    }
    @PostMapping("/insert")
    public CommonResponse insert(@RequestBody Room room ){
        room.setState("正常");
        roomService.insert(room);
        return  ResponseUtil.success("添加成功");
    }
    @PostMapping("/update")
    public CommonResponse update(@RequestBody Room room ){
        roomService.update(room);
        return  ResponseUtil.success("修改成功");
    }
    @GetMapping("/delete")
    public CommonResponse delete(@RequestParam("id") Integer id ){
        roomService.delete(id);
        return  ResponseUtil.success("删除成功");
    }
    @GetMapping("/updatestate")
    public CommonResponse updatestate(@RequestParam("id") Integer id){
        Room room=roomService.findById(id);
        if(room.getState().equals("正常")){
            room.setState("冻结");
        }else{
            room.setState("正常");
        }
        roomService.update(room);
        return  ResponseUtil.success("修改成功");
    }
}
