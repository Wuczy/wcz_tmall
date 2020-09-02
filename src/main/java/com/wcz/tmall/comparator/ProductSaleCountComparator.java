package com.wcz.tmall.comparator;

import com.wcz.tmall.pojo.Product;
import java.util.Comparator;

 /**
  * @Date:2020-08-28 20:15
  * @author: ChengZhi.Wu9299
  * @Description: 产品价格比较器，价格低的放前面
  */
public class ProductSaleCountComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getSaleCount() - o2.getSaleCount();
    }
}
