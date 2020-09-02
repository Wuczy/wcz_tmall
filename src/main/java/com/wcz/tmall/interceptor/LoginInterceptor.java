package com.wcz.tmall.interceptor;

import com.wcz.tmall.pojo.User;
import com.wcz.tmall.service.CategoryService;
import com.wcz.tmall.service.OrderItemService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

 /**
  * @Date:2020-08-29 23:27
  * @author: ChengZhi.Wu9299
  * @Description: 登陆状态拦截器
  */
public class LoginInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrderItemService orderItemService;


    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     * 在业务处理器处理请求之前被调用
     * 返回false，从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 返回true，执行下一个拦截器，知道所有的拦截器都执行完毕，再执行被被拦截的Controller，
     * 然后进入拦截器链，从最后一个拦截器往回执行所有的postHandle()，接着从最后一个拦截器往回执行的所有的afterCompletion()。
     *
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws IOException {
        HttpSession session = request.getSession();
        //获取访问的路径
        String contextPath = session.getServletContext().getContextPath();
        //不需要登陆可以访问的路径
        String[] noNeedAuthPage = new String[]{
                "home",
                "checkLogin",
                "register",
                "loginAjax",
                "login",
                "product",
                "category",
                "search"
        };
        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri,contextPath);
        if (uri.startsWith("/fore")){
            //获取/fore后面的字符串
            String method = StringUtils.substringAfterLast(uri,"/fore");
            if (!Arrays.asList(noNeedAuthPage).contains(method)){
                //所访问的路径为需要登陆才能访问的路径
                User user = (User) session.getAttribute("user");
                if (null==user){
                    response.sendRedirect("loginPage");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
