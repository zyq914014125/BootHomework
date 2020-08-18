package com.home.module.home2.H2Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author Mr.X
 **/
@Controller
@RequestMapping("H2")
public class TestController {

    /**
     * 页面初始化
     */
    @GetMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping("/filtertest")
    @ResponseBody
    public String FilterTest(HttpServletRequest request, @RequestParam String input, ModelMap map) {
        String input2 = request.getParameter("input");
        return "Test   " + input + "  and   " + input2;
    }

    @GetMapping("/interceptor")
    public String interceptortest() {
        return "index";
    }

    /**
     * @param file
     * @param redirectAttributes 传递msg信息
     * @return
     * @Description 文件上传测试
     */

    @PutMapping(value = "/file", consumes = "application/x-www-form-urlencoded")
    public String file(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    "message", "Please select file.");
            //空文件自动重定向回index
            return "redict:/H2/index";
        }
        try {
            String filePath = "G:\\manventest\\FileLoad" + file.getOriginalFilename();
            File destFile = new File(filePath);
            file.transferTo(destFile);
            redirectAttributes.addFlashAttribute(
                    "message", "Upload file success.");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute(
                    "message", "Upload file failed.");
        }
        return "redict:/H2/index";
    }

    @PutMapping(value = "/files", consumes = "application/x-www-form-urlencoded")
    public String files(@RequestParam MultipartFile[] file, RedirectAttributes redirectAttributes,ModelMap map) {
        map.addAttribute("page","H2/file");
        String filePath = "G:\\manventest\\FileLoad";
        if(file.length==0){
             redirectAttributes.addFlashAttribute(
                     "message", "你撒子都没选.");
             //空文件自动重定向回index
             return "redict:/H2/index";
         }
             try{
                 for(MultipartFile file1:file) {
                     File destFile = new File(filePath + file1.getOriginalFilename());
                     file1.transferTo(destFile);
                 }
                 redirectAttributes.addFlashAttribute(
                         "message", "Upload file success.");
             } catch (IOException e) {
                 redirectAttributes.addFlashAttribute(
                         "message", "Upload file failed.");
             }
          return "redict:/H2/index";
         }
    }
