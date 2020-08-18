package com.home.module.home1.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.home.Aspect.ServiceAspectAnnoaction;
import com.home.Serach.Result;
import com.home.Serach.Serachvo;
import com.home.module.home1.entity.City;
import com.home.module.home1.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
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

    /**
     * @param countryId
     * @param serachvo
     * @return 分页
     */

    public PageInfo<City> getCitiesBySearchVo(int countryId, Serachvo serachvo){
        PageHelper.startPage(serachvo.getCurrentPage(),serachvo.getPageSize());
        PageInfo<City> pageInfo=new PageInfo<>(cityMapper.getCitiesBySearchVo(serachvo));
        return Optional.ofNullable(pageInfo).orElse(new PageInfo<>());
    }

    /**
     * 新增
     * @param city
     * @return new Resulet<City>
     */
    @ServiceAspectAnnoaction
    @Transactional
    public Result<City> Insert(City city){
        cityMapper.Insert(city);
        return new Result<City>(Result.ResultState.SUCCESS_RESPONSE,"Insert success",city);
    }

    /**
     * 修改
     * @param city
     * @return
     */
    @Transactional
    public Result<City> Update(City city){
        cityMapper.updateDistrict(city);
        return new Result<City>(Result.ResultState.SUCCESS_RESPONSE,"Insert success",city);
    }

    /**
     * 删除
     * @param cityId
     * @return
     */
    @Transactional
    public Result<Object> Delete(int cityId){
        cityMapper.Delete(cityId);
        return new Result<Object>(Result.ResultState.SUCCESS_RESPONSE,"Insert success");
    }
}
