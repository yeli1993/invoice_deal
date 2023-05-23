package com.hanmanyi.invoice.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页 业务处理
 *
 * @author hanmanyi
 */
@Controller
public class SysIndexController{

    // 系统首页
    @GetMapping("/")
    public String index(HttpServletRequest request,ModelMap mmap){
    	//参数中如果带有file，则完成本地上传到临时文件夹，并返回文件名
//    	String filePath = request.getParameter("file");
//    	mmap.put("fileName", fileName);
    	
        return "index";
    }
    
    // 系统首页
    @GetMapping("/index")
    public String toIndex(){
        return "index";
    }
    // 系统首页
    @GetMapping("/main")
    public String main(){
        return "main";
    }
}
