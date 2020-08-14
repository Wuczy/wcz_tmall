package com.wcz.tmall.pojo;

 /**
  * @Date:2020-08-11 22:11
  * @author:ChengZhi.Wu9299
  * @Description:分类表实体类
  */
public class Category {
    private Integer id;
    private String name;

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
