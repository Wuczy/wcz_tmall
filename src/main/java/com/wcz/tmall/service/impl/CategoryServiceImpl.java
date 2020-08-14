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
    public List<Category> list(Page page) {
        return categoryMapper.list(page);
    }

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-13 21:04
     * @Description:返回分类表总数
     */
     @Override
     public int total() {
         return categoryMapper.total();
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

 }
