package com.home.module.home2.H2Controller;
import com.home.module.home2.vo.H2ReadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

/**
 * @author Mr.X
 **/
@Controller
public class H2ControllertoHtml {
@Autowired
  private  H2ReadConfig h1ReadConfig;
@RequestMapping("frist/homework")
public String H1homework(ModelMap m){
    Map<String,String>  map=h1ReadConfig.getAnswer();
    m.addAttribute("AnswerMap",map);
    return "H2/Simplehtml";
}

}
