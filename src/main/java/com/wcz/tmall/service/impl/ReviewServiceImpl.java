package com.wcz.tmall.service.impl;

import com.wcz.tmall.mapper.ReviewMapper;
import com.wcz.tmall.pojo.Review;
import com.wcz.tmall.pojo.User;
import com.wcz.tmall.service.ReviewService;
import com.wcz.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;
    @Override
    public void add(Review c) {
        reviewMapper.add(c);
    }

    @Override
    public void delete(int id) {
        reviewMapper.delete(id);
    }

    @Override
    public void update(Review c) {
        reviewMapper.update(c);
    }

    @Override
    public Review get(int id) {
        return reviewMapper.get(id);
    }

    @Override
    public List<Review> list(int pid) {
        List<Review> list = reviewMapper.list(pid);
        for (Review review : list) {
            User user = userService.get(review.getPid());
            review.setUser(user);
        }
        return list;
    }

    @Override
    public int getCount(int pid) {
        return reviewMapper.getCount(pid);
    }
}
