package com.wcz.tmall.service.impl;

import com.wcz.tmall.mapper.ProductImageMapper;
import com.wcz.tmall.pojo.ProductImage;
import com.wcz.tmall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

 /**
  * @Date:2020-08-16 22:24
  * @author:ChengZhi.Wu9299
  * @Description:产品图片service实现类
  */
 @Service
public class ProductImageServiceImpl implements ProductImageService {

     @Autowired
     private ProductImageMapper productImageMapper;
    @Override
    public void add(ProductImage pi) {
        productImageMapper.add(pi);
    }

    @Override
    public void delete(int id) {
        productImageMapper.delete(id);
    }

    @Override
    public List<ProductImage> list(int pid, String type) {
        return productImageMapper.list(pid, type);
    }

     @Override
     public ProductImage get(int id) {
         return productImageMapper.getById(id);
     }
 }
