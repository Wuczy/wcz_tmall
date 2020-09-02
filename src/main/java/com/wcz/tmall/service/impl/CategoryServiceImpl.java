package com.wcz.tmall.service.impl;

import com.wcz.tmall.mapper.CategoryMapper;
import com.wcz.tmall.pojo.Category;
import com.wcz.tmall.service.CategoryService;
import com.wcz.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
 /**
  * @Date:2020-08-11 22:19
  * @author:ChengZhi.Wu9299
  * @Description:分类表服务层实现类
  */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-13 21:04
     * @Description:分页查询分类表
     */
    @Override
    public List<Category> list() {
        return categoryMapper.list();
    }

     /**
      * @Author:ChengZhi.Wu9299
      * @Date:2020-08-14 11:17
      * @Description:新增分类
      */
     @Override
     public void add(Category category) {
         categoryMapper.add(category);
     }

     /**
      * @Author:ChengZhi.Wu9299
      * @Date:2020-08-14 12:18
      * @Description:删除分类
      */
     @Override
     public void delete(int id) {
         categoryMapper.delete(id);
     }

     /**
      * @Author:ChengZhi.Wu9299
      * @Date:2020-08-14 14:04
      * @Description:根据id返回当前分类对象
      */
     @Override
     public Category get(int id) {
         return categoryMapper.get(id);
     }

     /**
      * @Author:ChengZhi.Wu9299
      * @Date:2020-08-14 14:27
      * @Description:保存当前对象
      */
     @Override
     public void update(Category category) {
         categoryMapper.update(category);
     }

     /**
      * @Author:ChengZhi.Wu9299
      * @Date:2020-08-19 20:57
      * @Description:根据名字模糊查询分类
      */
     @Override
     public List<Category> like(String name) {
         return categoryMapper.like(name);
     }

 }
