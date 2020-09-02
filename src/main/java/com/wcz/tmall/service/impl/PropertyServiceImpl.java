package com.wcz.tmall.service.impl;

import com.wcz.tmall.mapper.PropertyMapper;
import com.wcz.tmall.pojo.Property;
import com.wcz.tmall.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

 /**
  * @Date:2020-08-14 17:18
  * @author:ChengZhi.Wu9299
  * @Description:属性管理service接口实现类
  */
@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyMapper propertyMapper;

    @Override
    public void add(Property property) {
        propertyMapper.add(property);
    }

    @Override
    public void deleteById(Integer id) {
        propertyMapper.deleteById(id);
    }

    @Override
    public void update(Property property) {
        propertyMapper.update(property);
    }

    @Override
    public Property get(Integer id) {
        return propertyMapper.get(id);
    }

    @Override
    public List<Property> list(Integer cid) {
        return propertyMapper.list(cid);
    }
}
