package com.home.module.home1.Controller.CityController;

import com.github.pagehelper.PageInfo;
import com.home.Serach.Serachvo;
import com.home.module.home1.Service.CityServiceImpl;
import com.home.module.home1.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mr.X
 **/
@RestController
@RequestMapping("/City")
public class CityControllerRest {
    @Autowired
    private CityServiceImpl cityService;
    @GetMapping("/getCity")
    public List<City> getCityBycountryId(int countryId){
        return cityService.getCitybyCountryId(countryId);
    }
    @PostMapping(value = "/getCityAndSerachvo", consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(@RequestParam("countryId") int countryId, @RequestParam("Serachvo") @RequestBody Serachvo searchVo) {
        return cityService.getCitiesBySearchVo(countryId, searchVo);
    }
}
