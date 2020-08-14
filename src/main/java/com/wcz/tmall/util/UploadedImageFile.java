package com.wcz.tmall.util;

import org.springframework.web.multipart.MultipartFile;

/**
  * @Date:2020-08-14 11:22
  * @author:ChengZhi.Wu9299
  * @Description:此类用户接收上传文件的注入
  * 这里的属性名称image必须和页面中的增加分类部分中的type="file"的name值保持一致。
  * <input id="categoryPic" accept="image/*" type="file" name="image" />
  */
public class UploadedImageFile {
    MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
