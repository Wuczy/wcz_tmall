package com.wcz.tmall.mapper;

import com.wcz.tmall.pojo.ProductImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
  * @Date:2020-08-16 21:50
  * @author:ChengZhi.Wu9299
  * @Description:产品图片mapper接口
  * 产品图片只提供list,add和delete方法，相关业务不需要用到edit和update方法
  */
public interface ProductImageMapper {

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 21:56
     * @Description:为当前产品添加图片
     */
     void add(ProductImage pi);

     /**
      * @Author:ChengZhi.Wu9299
      * @Date:2020-08-16 21:57
      * @Description:删除对应图片
      */
     void delete(int id);

     /**
      * @Author:ChengZhi.Wu9299
      * @Date:2020-08-16 21:59
      * @Description:展示当前图片对应的图片
      */
     List<ProductImage> list(@Param("pid") int pid, @Param("type") String type);

     ProductImage getById(int id);
}
