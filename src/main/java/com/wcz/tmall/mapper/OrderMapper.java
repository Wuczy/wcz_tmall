package com.wcz.tmall.mapper;

import com.wcz.tmall.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
  * @Date:2020-08-20 11:53
  * @author:ChengZhi.Wu9299
  * @Description:订单表mapper接口
  */
public interface OrderMapper {
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-20 11:55
     * @Description:添加订单
     */
    void add(Order c);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-20 11:55
     * @Description:删除订单
     */
    void delete(int id);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-20 11:55
     * @Description:更新订单
     */
    void update(Order c);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-20 11:55
     * @Description:根据id获取当前订单
     */
    Order get(int id);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-20 11:57
     * @Description:获取所有订单
     */
    List<Order> list();

    /**
     * @Author: ChengZhi.Wu9299
     * @Date: 2020-08-30 16:43
     * @Description: 获取当前用户的订单信息
     */
    List<Order> select(@Param("uid") int uid, @Param("status") String status);
}
