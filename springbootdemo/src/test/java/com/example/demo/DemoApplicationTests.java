package com.example.demo;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    void userTest() {
        User user = userMapper.selectById(4);
        System.out.println(user);

    }

    @Test
    void userTest2() {
        User user=new User();
        user.setName("赵四");
        user.setAddress("上海");
        user.setPhone("18210029298");
        Integer insert = userMapper.insert(user);
        System.out.println(insert);

    }
}
