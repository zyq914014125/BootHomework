package com.home.module.home1.Controller.CityController;

import com.github.pagehelper.PageInfo;
import com.home.Serach.Result;
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
@RequestMapping("/city")
public class CityControllerRest {
    @Autowired
    private CityServiceImpl cityService;

    /**
     * id查询
     * @param countryId
     * @return
     */
    @GetMapping("/getCityTest/{countryId}")
    public List<City> getCityBycountryIdTest(@PathVariable  int countryId){
        return cityService.getCitybyCountryId(countryId);
    }

    /**
     * 分页
     * @param countryId
     * @param searchVo
     * @return
     */

    @PostMapping(value = "/getCityAndSerachvo/{countryId}", consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVoTest(@PathVariable int countryId,  @RequestBody Serachvo searchVo) {
        return cityService.getCitiesBySearchVo(countryId, searchVo);
    }

    /**
     * 修改
     * 根据表单提交
     * @ModelAttribute 表单对象接收
     * @param city
     * @return
     */
    @PutMapping(value = "/update",consumes = "application/x-www-form-urlencoded")
    public Result<City> Update(@ModelAttribute City city ){
        return cityService.Update(city);
    }

    /**
     * 新增
     * @param city
     * @return
     */
    @PostMapping(value = "/insert",consumes = "application/json")
    public Result<City> Insert(@RequestBody City city){
        return cityService.Insert(city);
    }

    /**
     * 删除
     * @param cityId
     * @return
     */
    @DeleteMapping("/delete/{cityId}")
    public Result<Object> Delete(@PathVariable int cityId){
        return cityService.Delete(cityId);
    }


}
