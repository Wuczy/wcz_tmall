package com.wcz.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wcz.tmall.pojo.Category;
import com.wcz.tmall.service.CategoryService;
import com.wcz.tmall.util.ImageUtil;
import com.wcz.tmall.util.Page;
import com.wcz.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Category> cs = categoryService.list();
        int total = (int) new PageInfo<>(cs).getTotal();
        page.setTotal(total);
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

    /**
     * @Author:ChengZhi.Wu9299
     * @Date:2020-08-14 14:13
     * @Description:显示编辑页面
     */
     @RequestMapping("admin_category_edit")
     public String edit(int id,Model model) throws IOException {
         Category c= categoryService.get(id);
         model.addAttribute("c", c);
         return "admin/editCategory";
     }

     //这里如果图片不做选择就默认为不改，所以说图片只能修改，不能删除。
     @RequestMapping("admin_category_update")
     public String update(Category c, UploadedImageFile uploadedImageFile, HttpSession session) throws IOException {
         categoryService.update(c);
         MultipartFile image = uploadedImageFile.getImage();
         if (null!=image&&!image.isEmpty()){
             File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
             File file = new File(imageFolder,c.getId()+".jpg");
             image.transferTo(file);
             BufferedImage img = ImageUtil.change2jpg(file);
             ImageIO.write(img,"jpg",file);
         }
         return "redirect:/admin_category_list";
     }

     @RequestMapping("admin_category_find")
     public String like(String name,Model model,Page page){
         PageHelper.offsetPage(page.getStart(),page.getCount());
         List<Category> cs = categoryService.like(name);
         int total = (int) new PageInfo<>(cs).getTotal();
         page.setTotal(total);
         model.addAttribute("cs",cs);
         model.addAttribute("page",page);
         return "admin/listCategory";
     }
}
