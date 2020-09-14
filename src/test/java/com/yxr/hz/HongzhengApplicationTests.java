package com.yxr.hz;

import com.yxr.hz.entity.Order;
import com.yxr.hz.entity.Student;
import com.yxr.hz.service.OrderService;
import com.yxr.hz.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@MapperScan("com.yxr.hz.dao")
@SpringBootTest
class HongzhengApplicationTests {
@Autowired
private StudentService studentService;
@Autowired
private OrderService orderService;
    @Test
    void contextLoads() {
    }

}
