package com.example.demo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.UserMapper;

import com.example.demo.entity.User;

import com.example.demo.service.UserService;

import org.springframework.stereotype.Service;


/**
 * @ClassName: UserServiceImpl
 * @Description: 服务实现类
 * @Author: gaopengzhan
 * @Date: 2020-02-14 06:40:51
 * @Version: v1.0 文件初始创建
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



}