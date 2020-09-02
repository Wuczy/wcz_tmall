package com.wcz.tmall.mapper;

import com.wcz.tmall.pojo.Category;

import java.util.List;

/**
  * @Date:2020-08-11 22:12
  * @author:ChengZhi.Wu9299
  * @Description:分类表mapper接口
  */
public interface CategoryMapper {

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-13 21:02
     * @Description:查询分类表
     */
    List<Category> list();

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-14 11:13
     * @Description:增加新的分类
     */
    void add(Category category);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-14 12:18
     * @Description:删除分类
     */
    void delete(int id);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-14 14:04
     * @Description：根据id返回当前分类对象
     */
    Category get(int id);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-14 14:27
     * @Description:保存当前对象
     */
    void update(Category category);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-19 20:55
     * @Description:根据分类名称模糊查询
     */
    List<Category> like(String name);
}
