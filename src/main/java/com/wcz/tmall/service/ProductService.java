package com.wcz.tmall.service;

import com.wcz.tmall.pojo.Category;
import com.wcz.tmall.pojo.Product;
import com.wcz.tmall.pojo.ProductImage;

import java.util.List;

/**
  * @Date:2020-08-16 17:17
  * @author:ChengZhi.Wu9299
  * @Description:产品管理service接口
  */
public interface ProductService {

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 22:55
     * @Description:获取当前产品的第一个图片
     */
    ProductImage getImage(int p);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 17:25
     * @Description:添加产品
     */
    void add(Product product);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 17:25
     * @Description:根据id删除当前产品
     */
    void deleteById(Integer id);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 17:25
     * @Description:更新产品信息
     */
    void update(Product product);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 17:25
     * @Description:根据id获取当前产品对象
     */
    Product get(Integer id);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 17:26
     * @Description:根据分类表的id获取所有产品对象
     */
    List<Product> list(Integer cid);

    List<Product> like(String name,int cid);

    void fill(List<Category> categorys);

    void fill(Category category);

    void fillByRow(List<Category> categorys);

    void setSaleAndReviewNumber(Product p);

    List<Product> search(String name);


}
