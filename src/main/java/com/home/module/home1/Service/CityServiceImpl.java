package com.home.module.home1.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.home.Serach.Serachvo;
import com.home.module.home1.entity.City;
import com.home.module.home1.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Mr.X
 **/
@Service
public class CityServiceImpl {
    @Autowired
    private CityMapper cityMapper;

    public List<City> getCitybyCountryId(int countryId){
        return Optional.ofNullable(cityMapper.getCitiesByCountryId(countryId)).orElse(Collections.emptyList());
    }
    //分页
    public PageInfo<City> getCitiesBySearchVo(int countryId, Serachvo serachvo){
        PageHelper.startPage(serachvo.getCurrentPage(),serachvo.getPageSize());
        PageInfo<City> pageInfo=new PageInfo<>(cityMapper.getCitiesByCountryId(countryId));
        return Optional.ofNullable(pageInfo).orElse(new PageInfo<>());
    }

}
