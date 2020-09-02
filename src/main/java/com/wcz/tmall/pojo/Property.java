package com.wcz.tmall.pojo;

 /**
  * @Date:2020-08-14 16:18
  * @author:ChengZhi.Wu9299
  * @Description:在属性表中组合了一个分类表字段
  */
public class Property {
    private Integer id;
    private Integer cid;
    private String name;
    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name==null ? null : name.trim();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
