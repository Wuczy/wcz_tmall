package com.wcz.tmall.pojo;

import java.util.List;

/**
  * @Date:2020-08-11 22:11
  * @author:ChengZhi.Wu9299
  * @Description:分类表实体类
  */
public class Category {
    private Integer id;
    private String name;
    //前端显示页面所需字段
    //代表一个分类下的多个产品
    private List<Product> products;
    //一个分类下的多个List<Product>，提供这个属性是为了在首页树状导航d的分类名称右边显示推荐产品列表
    private List<List<Product>> productsByRow;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<List<Product>> getProductsByRow() {
        return productsByRow;
    }

    public void setProductsByRow(List<List<Product>> productsByRow) {
        this.productsByRow = productsByRow;
    }

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name==null ? null : name.trim());
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
