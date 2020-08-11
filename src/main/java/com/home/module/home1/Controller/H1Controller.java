package com.home.module.home1.Controller;

import com.home.module.home1.vo.H1ReadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author Mr.X
 **/
@Controller
public class H1Controller {
@Autowired
    H1ReadConfig h1ReadConfig;

@RequestMapping("Frist/homework")
public String H1homework(ModelMap m){
    Map<String,String>  map=h1ReadConfig.getAnswer();
    m.addAttribute("AnswerMap",map);
    return "H1/H1html";
}
}
