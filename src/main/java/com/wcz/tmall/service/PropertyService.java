package com.wcz.tmall.service;

import com.wcz.tmall.pojo.Property;

import java.util.List;

 /**
  * @Date:2020-08-14 17:18
  * @author:ChengZhi.Wu9299
  * @Description:属性管理service接口
  */
public interface PropertyService {
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-14 16:34
     * @Description:添加属性
     */
    void add(Property property);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-14 16:43
     * @Description:根据id删除属性
     */
    void deleteById(Integer id);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-14 16:46
     * @Description:保存当前属性
     */
    void update(Property property);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-14 16:47
     * @Description:根据id返回property对象
     */
    Property get(Integer id);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-14 16:48
     * @Description:返回当前分类下的全部属性
     */
    List<Property> list(Integer cid);
}
