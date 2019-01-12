package com.yst.mall.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yst.mall.common.response.IResult;
import com.yst.mall.common.response.ResultBean;
import com.yst.mall.util.layui.Constants;
import com.yst.mall.util.layui.FileUtil;
import com.yst.mall.web.util.PropertyUtil;

/**
 * Created by gameloft9 on 2017/12/28.
 */
@Controller
@Slf4j
public class UploadController {


    /**
     * 上传文件
     * @param file 上传文件
     * @param type 文件业务类型 "userFace"-用户头像
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public IResult upload(MultipartFile file, String type, String fileName) throws Exception {
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<String>(FileUtil.saveAndReturnUrl(file.getBytes(), Constants.AttachmentType.USER_FACE.value,fileName, PropertyUtil.getProperty("web_base_url"),PropertyUtil.getProperty("file_root_path")));
    }
}
