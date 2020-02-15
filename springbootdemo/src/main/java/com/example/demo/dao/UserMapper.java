package com.example.demo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.example.demo.entity.User;


/**
 * @ClassName: UserMapper
 * @Description: UserMapper类
 * @Author: gaopengzhan
 * @Date: 2020-02-14 06:40:51
 * @Version: v1.0 文件初始创建
 */
public interface UserMapper extends BaseMapper<User> {

    @Override
    Integer insert(User entity);
}
