package com.home.module.home2.H2Controller;

import com.home.module.home1.Service.CountryServiceImpl;
import com.home.module.home1.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mr.X
 **/
@Controller
@RequestMapping("country")
public class CountryController {
    @Autowired
    CountryServiceImpl countryService;


    @RequestMapping("/start")
    public String getPage(){
        return "H2/Country";
    }

    /**
     * @Description getcountry by id
     * @param countryId
     * @param map
     * @return
     */
    @GetMapping("/{countryId}")
    public String countryById(@PathVariable int countryId, ModelMap map){
        Country country=countryService.getCountryBycountryId(countryId);
         map.addAttribute("countryById",country);
         return "H2/Country";
   }

    /**
     * @Description getcountry by name
     * @param countryName
     * @param map
     * @return
     */
    @GetMapping("/country")
    public String countryByName(@RequestParam String countryName, ModelMap map){
        Country country=countryService.getCountryByCountry(countryName);
        map.addAttribute("countryByName",country);
        return "H2/Country";
    }

    /**
     * @Description getcountry by id
     * @Database redis
     * @param countryId
     * @param map
     * @return
     */
    @GetMapping("/redis/{countryId}")
    public String RediscountryById(@PathVariable int countryId, ModelMap map){
        Country country=countryService.mograteCountryByRedis(countryId);
        map.addAttribute("RedisCountryById",country);
        return "H2/Country";
    }
}
