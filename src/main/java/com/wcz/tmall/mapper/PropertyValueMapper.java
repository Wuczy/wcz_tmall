package com.wcz.tmall.mapper;

import com.wcz.tmall.pojo.PropertyValue;

import java.util.List;

/**
  * @Date:2020-08-18 20:05
  * @author:ChengZhi.Wu9299
  * @Description:产品属性值mapper接口
  * 属性值不涉及delete，只有修改添加属性值，删除就直接去属性管理直接删除属性
  */
public interface PropertyValueMapper {
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-18 20:06
     * @Description:添加属性值
     */
    void add(PropertyValue pv);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-18 20:07
     * @Description:更新当前属性值
     */
    void update(PropertyValue pv);

     /**
      * @Date:2020-08-18 20:08
      * @author:ChengZhi.Wu9299
      * @Description:获取当前产品的当前属性的值
      */
    PropertyValue get(PropertyValue pv);

     /**
      * @Date:2020-08-18 20:09
      * @author:ChengZhi.Wu9299
      * @Description:获取当前属性的所有值
      */
    List<PropertyValue> list(int pid);
}
