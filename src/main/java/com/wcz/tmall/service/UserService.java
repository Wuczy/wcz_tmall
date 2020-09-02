package com.wcz.tmall.service;

import com.wcz.tmall.pojo.User;

import java.util.List;

 /**
  * @Date:2020-08-19 9:42
  * @author:ChengZhi.Wu9299
  * @Description:用户管理service层接口
  */
public interface UserService {
    void add(User c);
    void delete(int id);
    void update(User c);
    User get(int id);
    List<User> list();
    List<User> like(String name);
     boolean isExist(String name);
     User getUser(String name);
}