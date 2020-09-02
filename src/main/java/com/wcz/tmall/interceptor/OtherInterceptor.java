package com.wcz.tmall.interceptor;

import com.wcz.tmall.pojo.Category;
import com.wcz.tmall.pojo.OrderItem;
import com.wcz.tmall.pojo.User;
import com.wcz.tmall.service.CategoryService;
import com.wcz.tmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

 /**
  * @Date:2020-08-29 23:28
  * @author: ChengZhi.Wu9299
  * @Description: 其他拦截器
  */
public class OtherInterceptor extends HandlerInterceptorAdapter {
    @Autowired
     private CategoryService categoryService;
    @Autowired
    private OrderItemService orderItemService;
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        return true;
 
    }
  
    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,   
            ModelAndView modelAndView) throws Exception {
        //这里是获取分类集合信息，用于放在搜索栏下面
        List<Category> cs = categoryService.list();
        request.getSession().setAttribute("cs", cs);
 
        //这里是获取当前的contextPath:tmall_ssm,用与放在左上角那个变形金刚，点击之后才能够跳转到首页，否则点击之后也仅仅停留在当前页面
        HttpSession session = request.getSession();
//        String contextPath=session.getServletContext().getContextPath();
//        System.out.println(contextPath);
//        request.getSession().setAttribute("contextPath", contextPath);
 
        //这里是获取购物车中一共有多少数量
        User user =(User)  session.getAttribute("user");
        int  cartTotalItemNumber = 0;
        if(null!=user) {
            List<OrderItem> ois = orderItemService.getByUid(user.getId());
            for (OrderItem oi : ois) {
                cartTotalItemNumber+=oi.getNumber();
            }
         
        }
        request.getSession().setAttribute("cartTotalItemNumber", cartTotalItemNumber);
 
    }
  
    /** 
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等  
     *  
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */
      
    public void afterCompletion(HttpServletRequest request,   
            HttpServletResponse response, Object handler, Exception ex) 
    throws Exception {

    }
}