package com.wcz.tmall.service.impl;

import com.wcz.tmall.mapper.PropertyMapper;
import com.wcz.tmall.mapper.PropertyValueMapper;
import com.wcz.tmall.pojo.Product;
import com.wcz.tmall.pojo.Property;
import com.wcz.tmall.pojo.PropertyValue;
import com.wcz.tmall.service.PropertyValueService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
 /**
  * @Date:2020-08-18 19:56
  * @author:ChengZhi.Wu9299
  * @Description:产品属性值service接口实现类
  */
@Service
public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    private PropertyValueMapper propertyValueMapper;
    @Autowired
    private PropertyMapper propertyMapper;
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-18 20:20
     * @Description:初始化属性值，因为对于PropertyValue的管理，没有增加，只有修改。
     * 所以需要通过初始化来进行自动地增加，以便于后面的修改。
     * 根据产品获取属性，在根据属性获取属性值，如果属性值不存在就创建一个属性值插入到数据库
     */
    @Override
    public void init(Product p) {
        List<Property> properties = propertyMapper.list(p.getCid());
        for (Property pt : properties) {
            PropertyValue propertyValue = new PropertyValue();
            propertyValue.setPid(p.getId());
            propertyValue.setPtid(pt.getId());
            PropertyValue pv = get(propertyValue);
            if(null==pv){
                pv = new PropertyValue();
                pv.setPid(p.getId());
                pv.setPtid(pt.getId());
                propertyValueMapper.add(pv);
            }
        }
    }

    @Override
    public void update(PropertyValue pv) {
        propertyValueMapper.update(pv);
        System.out.println("success");
    }

    @Override
    public PropertyValue get(PropertyValue pv) {
        return propertyValueMapper.get(pv);
    }

    @Override
    public List<PropertyValue> list(int pid) {
        List<PropertyValue> list = propertyValueMapper.list(pid);
        for (PropertyValue propertyValue : list) {
            propertyValue.setProperty(propertyMapper.get(propertyValue.getPtid()));
        }
        return list;
    }
}
