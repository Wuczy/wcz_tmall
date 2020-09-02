package com.wcz.tmall.comparator;

import com.wcz.tmall.pojo.Product;

import java.util.Comparator;

public class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int)(o1.getPromotePrice() - o2.getPromotePrice());
    }
}
