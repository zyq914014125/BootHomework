package com.home.module.home3.Controller;

import com.home.module.home3.Service.impl.UserServiceImpl;
import com.home.module.home3.entity.user;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;

/**
 * @author Mr.X
 **/
@Controller
public class loginController {
@Autowired
    UserServiceImpl userService;
    @GetMapping("/login/index")
    public String index(){
        return "login/index";
    }
    @GetMapping("/index")
    public String login(){
        return "/index";
    }
    @GetMapping("/error")
    public String error(){
        return "error/403";
    }
}
