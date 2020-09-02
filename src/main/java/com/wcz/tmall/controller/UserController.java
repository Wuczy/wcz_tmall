package com.wcz.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wcz.tmall.pojo.User;
import com.wcz.tmall.service.UserService;
import com.wcz.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
 /**
  * @Date:2020-08-19 10:22
  * @author:ChengZhi.Wu9299
  * @Description:用户管理控制层，只提供查询方法，修改用户删除用户新增用户等都应在前端进行
  */
@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("admin_user_list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<User> us= userService.list();
//        for (User u : us) {
//            String psd = DigestUtils.md5DigestAsHex(u.getPassword().getBytes());
//            u.setPassword(psd);
//            userService.update(u);
//            System.out.println(u);
//            "明文".equalsIgnoreCase("密文");
//          }
        int total = (int) new PageInfo<>(us).getTotal();
        page.setTotal(total);
        model.addAttribute("us", us);
        model.addAttribute("page", page);

        return "admin/listUser";
    }

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-19 10:52
     * @Description:模糊查询
     */
    @RequestMapping("admin_user_find")
    public String find(String name, Model model,Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<User> us= userService.like(name);
        int total = (int) new PageInfo<>(us).getTotal();
        page.setTotal(total);
        model.addAttribute("us", us);
        model.addAttribute("page", page);
        return "admin/listUser";
    }
}
