package com.wcz.tmall.comparator;

import com.wcz.tmall.pojo.Product;

import java.util.Comparator;

 /**
  * @Date:2020-08-28 20:13
  * @author: ChengZhi.Wu9299
  * @Description: 产品日期比较器，创建日期晚的放前面
  */
public class ProductDateComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getCreateDate().compareTo(o1.getCreateDate());
    }
}
