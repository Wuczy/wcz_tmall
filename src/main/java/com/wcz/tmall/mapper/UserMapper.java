package com.wcz.tmall.mapper;

import com.wcz.tmall.pojo.User;

import java.util.List;

 /**
  * @Date:2020-08-19 9:30
  * @author:ChengZhi.Wu9299
  * @Description:用户管理mapper接口
  */
public interface UserMapper {
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-19 9:31
     * @Description:添加用户
     */
    void add(User c);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-19 9:31
     * @Description:根据id删除用户
     */
    void delete(int id);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-19 9:32
     * @Description:修改用户信息
     */
    void update(User c);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-19 9:32
     * @Description:获取当前用户对象
     */
    User get(int id);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-19 9:33
     * @Description:获取全部list信息
     */
    List<User> list();

     /**
      * @Date:2020-08-19 10:49
      * @author:ChengZhi.Wu9299
      * @Description:模糊查询
      */
    List<User> like(String name);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-26 10:57
     * @Description:根据名字获取用户
     */
    User getByName(String name);


}
