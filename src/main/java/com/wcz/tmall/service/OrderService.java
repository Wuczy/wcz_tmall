package com.wcz.tmall.service;

import com.wcz.tmall.pojo.Order;
import com.wcz.tmall.pojo.OrderItem;

import java.util.List;

 /**
  * @Date:2020-08-20 11:51
  * @author:ChengZhi.Wu9299
  * @Description:订单表service接口
  */
public interface OrderService {

    /*订单状态的常量*/
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";
 
    void add(Order c);
    void delete(int id);
    Order get(int id);
    List<Order> list();
    void update(Order o);
    List<Order> list(int uid,String status);
    float add(Order o,List<OrderItem> ois);
}