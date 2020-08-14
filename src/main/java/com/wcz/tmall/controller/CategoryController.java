package com.wcz.tmall.controller;

import com.wcz.tmall.pojo.Category;
import com.wcz.tmall.service.CategoryService;
import com.wcz.tmall.util.ImageUtil;
import com.wcz.tmall.util.Page;
import com.wcz.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

 /**
  * @Date:2020-08-11 22:24
  * @author:ChengZhi.Wu9299
  * @Description:分类表控制层
  */
@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @RequestMapping("admin_category_list")
    public String list(Model model, Page page){
        List<Category> cs = categoryService.list(page);
        int total = categoryService.total();
        System.out.println(total);
        page.setTotal(total);
        System.out.println(page);
        model.addAttribute("cs",cs);
        model.addAttribute("page",page);
        return "admin/listCategory";
    }

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-14 11:43
     * @Param:uploadedImageFile用来接收上传的对象，session用户获取当前应用路径
     * @Description:添加分类
     */
    @RequestMapping("admin_category_add")
    public String add(Category c, UploadedImageFile uploadedImageFile, HttpSession session) throws IOException {
        categoryService.add(c);
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,c.getId()+".jpg");
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        //把浏览器传递过来的图片保存在img/category
        uploadedImageFile.getImage().transferTo(file);
        //确保图片格式一定是jpg，而不仅仅是后缀名是jpg
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img,"jpg",file);
        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_delete")
    public String delete(int id,HttpSession session){
        categoryService.delete(id);
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return "redirect:/admin_category_list";
    }
}
