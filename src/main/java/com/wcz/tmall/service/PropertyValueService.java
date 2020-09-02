package com.wcz.tmall.service;

import com.wcz.tmall.pojo.Product;
import com.wcz.tmall.pojo.PropertyValue;

import java.util.List;
 /**
  * @Date:2020-08-18 19:55
  * @author:ChengZhi.Wu9299
  * @Description:产品属性值service接口
  */
public interface PropertyValueService {
    void init(Product p);
    void update(PropertyValue pv);
    PropertyValue get(PropertyValue pv);
    List<PropertyValue> list(int pid);
}