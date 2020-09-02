package com.wcz.tmall.service.impl;

import com.wcz.tmall.mapper.UserMapper;
import com.wcz.tmall.pojo.User;
import com.wcz.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

 /**
  * @Date:2020-08-19 9:46
  * @author:ChengZhi.Wu9299
  * @Description:用户管理service接口实现类
  */
 @Service
public class UserServiceImpl implements UserService {
     @Autowired
     private UserMapper userMapper;

    @Override
    public void add(User c) {
        userMapper.add(c);
    }

    @Override
    public void delete(int id) {
        userMapper.delete(id);
    }

    @Override
    public void update(User c) {
        userMapper.update(c);
    }

    @Override
    public User get(int id) {
        return userMapper.get(id);
    }

    @Override
    public List<User> list() {
        return userMapper.list();
    }

     @Override
     public List<User> like(String name) {
         return userMapper.like(name);
     }

     /**
      * @Author:ChengZhi.Wu9299
      * @Date:2020-08-26 10:53
      * @param name 用户名
      * @Description:判断用户是否存在 true为存在 false未不存在
      */
     @Override
     public boolean isExist(String name) {
         User user = userMapper.getByName(name);
         return !(user==null);
     }

     @Override
     public User getUser(String name) {
         return userMapper.getByName(name);
     }

 }
