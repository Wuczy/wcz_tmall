package com.wcz.tmall.comparator;

import com.wcz.tmall.pojo.Product;

import java.util.Comparator;

 /**
  * @Date:2020-08-28 20:11
  * @author: ChengZhi.Wu9299
  * @Description: 产品评价数比较器，评价多的放前面
  */
public class ProductReviewComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getReviewCount() - o2.getReviewCount();
    }
}
