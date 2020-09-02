package com.wcz.tmall.comparator;

import com.wcz.tmall.pojo.Product;

import java.util.Comparator;

 /**
  * @Date: 2020-08-28 20:28
  * @author: ChengZhi.Wu9299
  * @Description: 综合比较器，销量和评价按7：3的比列权重比较，高的放前面
  */
public class ProductAllComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        double f1 = o1.getSaleCount()*0.7 + o1.getReviewCount()*0.3;
        double f2 = o1.getSaleCount()*0.7 + o1.getReviewCount()*0.3;
        return (int)(f1 - f2);
    }
}
