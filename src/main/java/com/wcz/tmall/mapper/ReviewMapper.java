package com.wcz.tmall.mapper;

import com.wcz.tmall.pojo.Review;

import java.util.List;

/**
  * @Date:2020-08-26 20:53
  * @author:ChengZhi.Wu9299
  * @Description:评论表mapper接口
  */
public interface ReviewMapper {
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-26 20:54
     * @Description:添加评论
     */
    void add(Review c);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-26 20:54
     * @Description:删除评论
     */
    void delete(int id);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-26 20:54
     * @Description:修改评论
     */
    void update(Review c);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-26 20:54
     * @Description:根据id获取评论
     */
    Review get(int id);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-26 20:54
     * @Description:获取当前产品的所有评论
     */
    List<Review> list(int pid);
    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-26 20:56
     * @Description:返回当前产品的评论的总数
     */
    int getCount(int pid);
}
