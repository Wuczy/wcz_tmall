package com.wcz.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.wcz.tmall.comparator.*;
import com.wcz.tmall.pojo.*;
import com.wcz.tmall.service.*;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class ForeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private PropertyValueService propertyValueService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ReviewService reviewService;
    private HttpSession session;

    @RequestMapping("forehome")
    public String home(Model model) {
        List<Category> cs= categoryService.list();
        productService.fill(cs);
        productService.fillByRow(cs);
         model.addAttribute("cs", cs);
        return "fore/home";
    }

    @RequestMapping("foreregister")
    public String register(User user,Model model){
        String name = user.getName();
        //将特殊字符转换为HTML字符引用。
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist = userService.isExist(name);
        if (exist){
            //存在
            String msg = "用户名已经被使用，请换一个用户名。";
            model.addAttribute("msg",msg);
            model.addAttribute("user",null);
            return "fore/register";
        }
        userService.add(user);
        return "redirect:registerSuccessPage";
    }

    @RequestMapping("forelogin")
    public String login(@RequestParam("name") String name,String password, Model model, HttpSession session){
        name = HtmlUtils.htmlEscape(name);
        //password = HtmlUtils.htmlEscape(password);
        User user = userService.getUser(name);
        if (user == null || !(password.equals(user.getPassword()))){
            //不存在
            String msg = "账号或密码错误！";
            model.addAttribute("msg",msg);
            return "fore/login";
        }
        session.setAttribute("user",user);
        return "redirect:forehome";
    }

    @RequestMapping("forelogout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:forehome";
    }

    @RequestMapping("foreproduct")
    public String product( int pid, Model model) {
        Product p = productService.get(pid);
        List<ProductImage> productSingleImages = productImageService.list(p.getId(), ProductImageService.type_single);
        List<ProductImage> productDetailImages = productImageService.list(p.getId(), ProductImageService.type_detail);
        p.setProductSingleImages(productSingleImages);
        p.setProductDetailImages(productDetailImages);
        p.setFirstProductImage(productService.getImage(p.getId()));
        List<PropertyValue> pvs = propertyValueService.list(p.getId());
        List<Review> reviews = reviewService.list(p.getId());
        productService.setSaleAndReviewNumber(p);
        model.addAttribute("reviews", reviews);
        model.addAttribute("p", p);
        model.addAttribute("pvs", pvs);
        return "fore/product";
    }

     /**
      * @Date: 2020-08-28 19:42
      * @author: ChengZhi.Wu9299
      * @Description: 产品页面添加购物车和购买时验证是否登陆
      * @param session
     */
    @RequestMapping("forecheckLogin")
    @ResponseBody
    public String checkLogin(HttpSession session){
        User user = (User)session.getAttribute("user");
        if (user==null){
            return "fail";
        }
        return "success";
    }

    /**
     * @Author: ChengZhi.Wu9299
     * @Date:2020-08-28 19:48
     * @Description: 产品页面添加购物车和购买时验证没登陆登陆，访问此路径进行Ajax异步登陆
     */
    @RequestMapping("foreloginAjax")
    @ResponseBody
    public String loginAjax(@RequestParam("name") String name, @RequestParam("password") String password,
                            HttpSession session){
        User user = userService.getUser(name);
        if (user == null || !(password.equals(user.getPassword()))){
            return "fail";
        }
        session.setAttribute("user",user);
        return "success";
    }

    /**
     * @Author: ChengZhi.Wu9299
     * @Date: 2020-08-28 21:45
     * @Description: 产品分类，根据销量、评价等来排序
     */
    @RequestMapping("forecategory")
    public String category(int cid,String sort,Model model){
        Category c= categoryService.get(cid);
        //为当前分类填充产品
        productService.fill(c);
        //为当前产品填充销量和评论
        List<Product> products = c.getProducts();
        for (Product product : products) {
            //设置销量和评价数
            productService.setSaleAndReviewNumber(product);
        }
        if (null!=sort){
            switch (sort){
                case "review":
                    Collections.sort(products,new ProductReviewComparator());
                    break;
                case "date":
                    Collections.sort(products,new ProductDateComparator());
                    break;
                case "saleCount":
                    Collections.sort(products,new ProductSaleCountComparator());
                    break;
                case "price":
                    Collections.sort(products,new ProductPriceComparator());
                    break;
                case "all":
                    Collections.sort(products,new ProductAllComparator());
                    break;
            }
        }
        //c.setProducts(products);
        model.addAttribute("c",c);
        return "fore/category";
    }

    @RequestMapping("foresearch")
    public String search(String keyword,Model model){
        PageHelper.offsetPage(0,20);
        List<Product> ps = productService.search(keyword);
        for (Product p : ps) {
            productService.setSaleAndReviewNumber(p);
            p.setFirstProductImage(productService.getImage(p.getId()));
        }
        model.addAttribute("ps",ps);
        return "fore/searchResult";
    }

    /**
     * @param pid 产品编号
     * @param num 购买数量
     * @param session
     * @return
     * 用户直接购买调用方法，如果已存在这个产品对应的orderItem，但没生成订单，即还在购物车中，
     * 那么就在对应的orderItem上增加数量；如果不存在，就新增一个orderItem。
     */
    @RequestMapping("forebuyone")
    public String buyone(int pid,int num,HttpSession session){
        //订单项的id
        int oiid = 0;
        User user = (User)session.getAttribute("user");
        boolean found = false;
        List<OrderItem> orderItems = orderItemService.getByUid(user.getId());
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getPid() == pid){
                //用户存在这个产品的orderItem。
                orderItem.setNumber(orderItem.getNumber() + num);
                orderItemService.update(orderItem);
                found = true;
                oiid = orderItem.getId();
                break;
            }
        }
        if (!found){
            //用户不存在这个产品的orderItem
            OrderItem oi = new OrderItem();
            oi.setNumber(num);
            oi.setPid(pid);
            oi.setUid(user.getId());
            orderItemService.add(oi);
            oiid = oi.getId();
        }
        return "redirect:forebuy?oiid="+oiid;
    }


    /**
     * @param model
     * @param oiid 订单项编号String数组，因为购物车中可以多选
     * @param session
     * @return
     * 点击立即购买后，调用此方法转到结算界面，填写信息
     */
    @RequestMapping("forebuy")
    public String buy(Model model,String[] oiid,HttpSession session){
        List<OrderItem> orderItems = new ArrayList<>();
        float total = 0;
        for (String s : oiid) {
            int id = Integer.parseInt(s);
            OrderItem orderItem = orderItemService.get(id);
            Product product = productService.get(orderItem.getPid());
            ProductImage image = productService.getImage(product.getId());
            product.setFirstProductImage(image);
            orderItem.setProduct(product);
            orderItems.add(orderItem);
            total += orderItem.getNumber() * orderItem.getProduct().getPromotePrice();
        }
        session.setAttribute("ois",orderItems);
        model.addAttribute("total",total);
        return "fore/buy";
    }

    /**
     * @param pid
     * @param num
     * @param session
     * @return
     * 添加购物车按钮，和立即购买所做的业务一样，只不过返回的是一个success的字符串
     */
    @RequestMapping("foreaddCart")
    @ResponseBody
    public String addCart(int pid,int num,HttpSession session){
        Product product = productService.get(pid);
        User user = (User) session.getAttribute("user");
        boolean found = false;
        List<OrderItem> orderItems = orderItemService.getByUid(user.getId());
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getPid() == pid){
                //用户存在这个产品的orderItem。
                orderItem.setNumber(orderItem.getNumber() + num);
                orderItemService.update(orderItem);
                found = true;
                break;
            }
        }
        if (!found){
            //用户不存在这个产品的orderItem
            OrderItem oi = new OrderItem();
            oi.setNumber(num);
            oi.setPid(pid);
            oi.setUid(user.getId());
            orderItemService.add(oi);
        }
        return "success";
    }

    /**
     * @param session
     * @param model
     * @return
     * 显示购物车
     */
    @RequestMapping("forecart")
    public String cart(HttpSession session,Model model){
        User user = (User)session.getAttribute("user");
        List<OrderItem> orderItems = orderItemService.getByUid(user.getId());
        model.addAttribute("ois",orderItems);
        return "fore/cart";
    }

    /**
     * @param pid
     * @param number
     * @param session
     * @return
     * 购物车中订单数量的增加或减少，Ajax调用此方法
     */
    @RequestMapping("forechangeOrderItem")
    @ResponseBody
    public String changeOrderItem(Integer pid,Integer number,HttpSession session){
        User user = (User) session.getAttribute("user");
        List<OrderItem> orderItems = orderItemService.getByUid(user.getId());
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getPid().intValue() == pid){
                orderItem.setNumber(number);
                orderItemService.update(orderItem);
                break;
            }
        }
        return "success";
    }


    /**
     * @param oiid
     * @return
     * 删除订单项
     */
    @RequestMapping("foredeleteOrderItem")
    @ResponseBody
    public String deleteOrderItem(int oiid){
        orderItemService.delete(oiid);
        return "success";
    }

    /**
     * @param order
     * @param session
     * @return 生成订单，客户端跳转到路径/forealipay方法
     */
    @RequestMapping("forecreateOrder")
    public String createOrder(Order order,HttpSession session){
        User user = (User) session.getAttribute("user");
        //根据当前时间和随机四位数生成订单号
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+
                           RandomUtils.nextInt(10000);
        order.setOrderCode(orderCode);
        order.setCreateDate(new Date());
        order.setUid(user.getId());
        order.setStatus(OrderService.waitPay);
        List<OrderItem> ois= (List<OrderItem>)session.getAttribute("ois");
        float total = orderService.add(order, ois);
        return "redirect:forealipay?oid="+order.getId() +"&total="+total;

    }

    /**
     * @param oid
     * @param model
     * @return 支付成功后跳转到支付成功页面
     */
    @RequestMapping("forepayed")
    public String payed(int oid,Model model) {
        Order order = orderService.get(oid);
        order.setStatus(OrderService.waitDelivery);
        order.setPayDate(new Date());
        orderService.update(order);
        model.addAttribute("o", order);
        return "fore/payed";
    }


    /**
     * @param model
     * @param session
     * @return 显示用户订单信息
     */
    @RequestMapping("forebought")
    public String bought( Model model,HttpSession session) {
        User user =(User)  session.getAttribute("user");
        List<Order> os= orderService.list(user.getId(),OrderService.delete);
        for (Order o : os) {
            orderItemService.fill(o);
        }
        model.addAttribute("os", os);
        return "fore/bought";
    }


    /**
     * @param model
     * @param oid
     * @return 确认支付订单后, 服务端跳转到confirmPay.jsp
     */
    @RequestMapping("foreconfirmPay")
    public String confirmPay( Model model,int oid) {
        Order o = orderService.get(oid);
        orderItemService.fill(o);
        model.addAttribute("o", o);
        return "fore/confirmPay";
    }

    /**
     * @param oid
     * @return 确认收货
     */
    @RequestMapping("foreorderConfirmed")
    public String orderConfirmed(int oid) {
        Order o = orderService.get(oid);
        o.setStatus(OrderService.waitReview);
        o.setConfirmDate(new Date());
        orderService.update(o);
        return "fore/orderConfirmed";
    }

    /**
     * @param oid
     * @return 删除订单
     */
    @RequestMapping("foredeleteOrder")
    @ResponseBody
    public String deleteOrder(int oid){
        Order o = orderService.get(oid);
        o.setStatus(OrderService.delete);
        orderService.update(o);
        return "success";
    }


    /**
     * @param model
     * @param oid
     * @return 评价产品页面
     */
    @RequestMapping("forereview")
    public String review( Model model,int oid) {
        Order o = orderService.get(oid);
        orderItemService.fill(o);
        Product p = o.getOrderItems().get(0).getProduct();
        ProductImage image = productService.getImage(p.getId());
        p.setFirstProductImage(image);
        List<Review> reviews = reviewService.list(p.getId());
        productService.setSaleAndReviewNumber(p);
        model.addAttribute("p", p);
        model.addAttribute("o", o);
        model.addAttribute("reviews", reviews);
        return "fore/review";
    }

    /**
     * @param session
     * @param oid
     * @param pid
     * @param content
     * @return 提交评论
     */
    @RequestMapping("foredoreview")
    public String doreview(HttpSession session,@RequestParam("oid") int oid,@RequestParam("pid") int pid,String content) {
        Order o = orderService.get(oid);
        o.setStatus(OrderService.finish);
        orderService.update(o);
        content = HtmlUtils.htmlEscape(content);
        User user =(User)  session.getAttribute("user");
        Review review = new Review();
        review.setContent(content);
        review.setPid(pid);
        review.setCreateDate(new Date());
        review.setUid(user.getId());
        reviewService.add(review);
        return "redirect:forereview?oid="+oid+"&showonly=true";
    }

}



