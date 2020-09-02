package com.wcz.tmall.service.impl;

import com.wcz.tmall.mapper.OrderMapper;
import com.wcz.tmall.pojo.Order;
import com.wcz.tmall.pojo.OrderItem;
import com.wcz.tmall.pojo.User;
import com.wcz.tmall.service.OrderItemService;
import com.wcz.tmall.service.OrderService;
import com.wcz.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

 /**
  * @Date:2020-08-20 13:55
  * @author:ChengZhi.Wu9299
  * @Description:订单service实现类
  */
 @Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderItemService orderItemService;

    @Override
    public void add(Order c) {
        orderMapper.add(c);
    }

    @Override
    public void delete(int id) {
        orderMapper.delete(id);
    }

    @Override
    public void update(Order o) {
        orderMapper.update(o);
    }

     @Override
     public List<Order> list(int uid, String status) {
        return orderMapper.select(uid,status);
        }

     @Override
    public Order get(int id) {
        return orderMapper.get(id);
    }

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-20 14:00
     * @Description:查询所有订单，并给订单设置对象的用户属性
     */
    @Override
    public List<Order> list() {
        List<Order> orders = orderMapper.list();
        for (Order order : orders) {
            User user = userService.get(order.getUid());
            order.setUser(user);
        }
        return orders;
    }


     /**
      * @param o
      * @param ois
      * @return
      * 增加订单业务，需要为order表新增数据，同时需要修改orderItem表，所以需要进行事物控制。
      */
     @Override
     @Transactional(propagation = Propagation.REQUIRED,rollbackForClassName = "Exception")
     public float add(Order o, List<OrderItem> ois) {
         float total = 0;
         orderMapper.add(o);

         for (OrderItem orderItem : ois) {
             orderItem.setOid(o.getId());
             orderItemService.update(orderItem);
             total += orderItem.getNumber() * orderItem.getProduct().getPromotePrice();
         }
         return total;
     }
 }
