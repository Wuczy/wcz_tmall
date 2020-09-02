package com.wcz.tmall.service.impl;

import com.wcz.tmall.mapper.CategoryMapper;
import com.wcz.tmall.mapper.ProductMapper;
import com.wcz.tmall.pojo.Category;
import com.wcz.tmall.pojo.Product;
import com.wcz.tmall.pojo.ProductImage;
import com.wcz.tmall.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

 /**
  * @Date:2020-08-16 17:20
  * @author:ChengZhi.Wu9299
  * @Description:产品管理service层实现类
  * 产品管理service类中，list和add方法都会把取出来的Product对象设置上对应的category
  */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ReviewService reviewService;

     @Override
     public ProductImage getImage(int pid) {
         return productMapper.getImage(pid);
     }

     /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 17:27
     * @Description:添加产品
     */
    @Override
    public void add(Product property) {
        productMapper.add(property);
    }

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 17:29
     * @Description:根据id删除当前产品
     */
    @Override
    public void deleteById(Integer id) {
        productMapper.deleleByid(id);
    }

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 17:29
     * @Description:更新当前产品
     */
    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 17:29
     * @Description:根据id获取当前对象
     */
    @Override
    public Product get(Integer id) {
        Product p = productMapper.get(id);
        int cid = p.getCid();
        Category c = categoryService.get(cid);
        p.setCategory(c);
        return p;
    }

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-16 17:30
     * @Description:根据产品表id获取所有产品
     */
    @Override
    public List<Product> list(Integer cid) {
        List<Product> list = productMapper.list(cid);
        for (Product product : list) {
            product.setCategory(categoryService.get(cid));
        }
        return list;
    }

     @Override
     public List<Product> like(String name, int cid) {
        return productMapper.like( name,cid);
     }

     /**
      * @Author:ChengZhi.Wu9299
      * @Date:2020-08-24 18:13
      * @param categories 分类集合
      * @Description: 给多个分类添加产品
      */
     @Override
     public void fill(List<Category> categories) {
         for (Category category : categories) {
             fill(category);
         }
     }

     /**
      * @Author:ChengZhi.Wu9299
      * @Date:2020-08-24 18:15
      * @param category 单个分类
      * @Description:为分类填充产品集合
      */
     @Override
     public void fill(Category category) {
         List<Product> products = productMapper.list(category.getId());
         for (Product product : products) {
             product.setFirstProductImage(productMapper.getImage(product.getId()));
         }
         category.setProducts(products);
     }

     /**
      * @Author:ChengZhi.Wu9299
      * @Date:2020-08-24 18:16
      * @param categories 分类集合
      * @Description: 给多个分类添加推荐产品集合,即把分类下的产品集合，按照8个为一行，拆成多行，以利于后续页面上进行显示
      */
     @Override
     public void fillByRow(List<Category> categories) {
         int productNumberEachRow = 8;
         for (Category c : categories) {
             List<Product> products =  c.getProducts();
             List<List<Product>> productsByRow =  new ArrayList<>();
             for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                 int size = i+productNumberEachRow;
                 size= size>products.size()?products.size():size;
                 List<Product> productsOfEachRow =products.subList(i, size);
                 productsByRow.add(productsOfEachRow);
             }
             c.setProductsByRow(productsByRow);
         }
     }

     /**
      * @Author:ChengZhi.Wu9299
      * @Date:2020-08-26 21:45
      * @Description:为产品设置销量和评价数量
      */
     @Override
     public void setSaleAndReviewNumber(Product p) {
         int saleCount = orderItemService.getSaleCount(p.getId());
         p.setSaleCount(saleCount);
         int reviewCount = reviewService.getCount(p.getId());
         p.setReviewCount(reviewCount);
     }

     @Override
     public List<Product> search(String name) {
         return productMapper.search(name);
     }
 }
