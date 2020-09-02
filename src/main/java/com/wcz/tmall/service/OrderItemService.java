package com.wcz.tmall.service;

import com.wcz.tmall.pojo.Order;
import com.wcz.tmall.pojo.OrderItem;

import java.util.List;

/**
  * @Date:2020-08-20 10:34
  * @author:ChengZhi.Wu9299
  * @Description:订单项表service接口
  */
public interface OrderItemService {
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-20 10:53
     * @Description:添加订单项
     */
    void add(OrderItem c);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-20 10:59
     * @Description:根据id删除当前订单项
     */
    void delete(int id);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-20 10:59
     * @Description:更新当前订单项
     */
    void update(OrderItem c);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-20 11:02
     * @Description:根据id获取当前订单项
     */
    OrderItem get(int id);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-20 11:03
     * @Description:获取所有订单项
     */
    List<OrderItem> list();

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-20 11:08
     * @Description:遍历订单下的所有订单项
     */
    void fill(Order o);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-26 21:15
     * @Description:根据产品获取销售量
     */
    int getSaleCount(int pid);

    List<OrderItem> getByUid(int uid);
}
