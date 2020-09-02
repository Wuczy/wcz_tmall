package com.wcz.tmall.service;

import com.wcz.tmall.pojo.ProductImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
  * @Date:2020-08-16 22:23
  * @author:ChengZhi.Wu9299
  * @Description:产品图片service接口
  */
public interface ProductImageService {
    //产品图片service接口提供两个类型常量
    String type_single = "type_single";
    String type_detail = "type_detail";
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 22:23
     * @Description:为当前产品添加图片
     */
    void add(ProductImage pi);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 22:23
     * @Description:删除对应图片
     */
    void delete(int id);

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 22:23
     * @Description:展示当前图片对应的图片
     */
    List<ProductImage> list(@Param("pid") int pid, @Param("type") String type);

    ProductImage get(int id);
}
