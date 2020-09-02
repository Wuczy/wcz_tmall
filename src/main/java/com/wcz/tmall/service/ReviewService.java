package com.wcz.tmall.service;

import com.wcz.tmall.pojo.Review;

import java.util.List;

public interface ReviewService {
      
    void add(Review c);
    void delete(int id);
    void update(Review c);
    Review get(int id);
    List<Review> list(int pid);
    int getCount(int pid);
}