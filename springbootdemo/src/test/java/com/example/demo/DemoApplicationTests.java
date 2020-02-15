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

    /**
     * @Description: 从数据库获得加密数据，经过dao层进行自动解密
    
     * @Date: 2020/2/15 10:35
     * @Author: fuguowen
     * @Return 
     * @Throws 
     */
    @Test
    void userTest() {
        User user = userMapper.selectById(4);
        System.out.println(user);

    }

    /**
     * @Description: 创建对象，操作dao层自动对数据加密，插入数据库
    
     * @Date: 2020/2/15 10:35
     * @Author: fuguowen
     * @Return 
     * @Throws 
     */
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
