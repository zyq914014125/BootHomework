package com.home.module.home1.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.X
 **/
@Component
@PropertySource("classpath:config/H1answer.properties")
//@ConfigurationProperties(prefix = "answer.step")
public class H1ReadConfig {
    @Value("${answer.step.name}")
    private String name;
    @Value("${answer.step.valuefirst}")
    private String valuefirst;
    @Value("${answer.step.valuesecond}")
    private String valuesecond;
    @Value("${answer.step.valuethrid}")
    private String valuethrid;
    @Value("${answer.step.valuefourth}")
    private String valuefourth;


    public Map<String,String> getAnswer(){
        String [] namelist=name.split("/n");
        Map<String,String> map=new HashMap<>();
        map.put(namelist[0],valuefirst);
        map.put(namelist[1],valuesecond);
        map.put(namelist[2],valuethrid);
        map.put(namelist[3],valuefourth);
        System.out.println(name);
        return map;
    }
}
