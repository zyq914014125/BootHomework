package com.home.module.home2.H2Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mr.X
 **/
@Controller
@RequestMapping("H2")
public class TestController {

    @RequestMapping("/filtertest")
    @ResponseBody
    public String FilterTest(HttpServletRequest request,@RequestParam String input, ModelMap map){
        String input2 =request.getParameter("input");
        return "Test   "+input+"  and   "+input2;
    }
    @GetMapping("/interceptor")
    public String interceptortest(){
        return "index";
    }

}
