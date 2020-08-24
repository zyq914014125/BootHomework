package com.home.module.home3.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mr.X
 **/
@Controller
@RequestMapping("menu")
public class MenuController {
    @GetMapping("/profile")
    public String profile(){
        return "index";
    }
    @GetMapping("/userTable")
    public String userTable(){
        return "index";
    }

}
