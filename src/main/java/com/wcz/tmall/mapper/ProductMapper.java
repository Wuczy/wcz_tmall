package com.wcz.tmall.mapper;

import com.wcz.tmall.pojo.Product;
import com.wcz.tmall.pojo.ProductImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
  * @Date:2020-08-16 16:56
  * @author:ChengZhi.Wu9299
  * @Description:产品mapper接口
  */
public interface ProductMapper {

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 16:58
     * @Description:批量查询产品
     */
    List<Product> list(int cid);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 16:59
     * @Description:根据id获取当前产品
     */
    Product get(int id);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 16:59
     * @Description:添加产品
     */
    void add(Product p);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 16:59
     * @Description:根据id删除当前产品
     */
    void deleleByid(int id);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 17:00
     * @Description:修改当前产品信息
     */
    void update(Product p);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 22:55
     * @Description:获取当前产品的第一个图片
     */
    ProductImage getImage(int pid);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-19 21:04
     * @Description:根据名字模糊查询当前产品的属性
     */
    List<Product> like(@Param("name") String name, @Param("cid") int cid);

    List<Product> search(String name);


}
