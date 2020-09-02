package com.wcz.tmall.mapper;

import com.wcz.tmall.pojo.OrderItem;

import java.util.List;

 /**
  * @Date:2020-08-20 10:16
  * @author:ChengZhi.Wu9299
  * @Description:订单项表mapper接口
  */
public interface OrderItemMapper {
    void add(OrderItem c);
    void delete(int id);
    void update(OrderItem c);
    OrderItem getById(int id);
    List<OrderItem> list();
    List<OrderItem> getByOid(int oid);
    List<OrderItem> getByPid(int Pid);
    List<OrderItem> getByUid(int uid);
}
