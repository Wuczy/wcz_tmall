package com.wcz.tmall.service.impl;

import com.wcz.tmall.mapper.OrderItemMapper;
import com.wcz.tmall.pojo.Order;
import com.wcz.tmall.pojo.OrderItem;
import com.wcz.tmall.pojo.Product;
import com.wcz.tmall.pojo.ProductImage;
import com.wcz.tmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
 /**
  * @Date:2020-08-20 11:03
  * @author:ChengZhi.Wu9299
  * @Description:订单项service实现类
  */
 @Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ProductServiceImpl productService;

    @Override
    public void add(OrderItem c) {
        orderItemMapper.add(c);
    }

    @Override
    public void delete(int id) {
        orderItemMapper.delete(id);
    }

    @Override
    public void update(OrderItem c) {
        orderItemMapper.update(c);
    }

    @Override
    public OrderItem get(int id) {
        return orderItemMapper.getById(id);
    }

    @Override
    public List<OrderItem> list() {
        return orderItemMapper.list();
    }

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-20 11:15
     * @Description:遍历订单下的订单项
     */
     @Override
     public void fill(Order o) {
         //1. 根据订单id查询出其对应的所有订单项
         List<OrderItem> orderItems = orderItemMapper.getByOid(o.getId());
         //2. 通过setProduct为所有的订单项设置Product属性
         //3. 遍历所有的订单项，然后计算出该订单的总金额和总数量
         float total = 0;
         int totalNumber = 0;
         for (OrderItem orderItem : orderItems) {
             Product product = productService.get(orderItem.getPid());
             product.setFirstProductImage(productService.getImage(orderItem.getPid()));
             orderItem.setProduct(product);
             total += orderItem.getNumber() * product.getPromotePrice();
             totalNumber += orderItem.getNumber();
         }
         //4. 把订单项设置在订单的orderItems属性上。
         o.setTotal(total);
         o.setTotalNumber(totalNumber);
         o.setOrderItems(orderItems);
     }

     @Override
     public int getSaleCount(int pid) {
         List<OrderItem> byPid = orderItemMapper.getByPid(pid);
         return byPid.size();
     }


     /**
      * @param uid
      * @return
      * 获取当前用户的订单项
      */
     @Override
     public List<OrderItem> getByUid(int uid) {
         List<OrderItem> orderItems = orderItemMapper.getByUid(uid);
         for (OrderItem orderItem : orderItems) {
             Product product = productService.get(orderItem.getPid());
             ProductImage image = productService.getImage(product.getId());
             product.setFirstProductImage(image);
             orderItem.setProduct(product);
         }
         return orderItems;
     }
 }
