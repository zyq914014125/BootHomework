package com.home.module.home1.Service;

import com.home.Utils.RedisUtils;
import com.home.module.home1.entity.Country;
import com.home.module.home1.mapper.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Mr.X
 **/
@Service
public class CountryServiceImpl {
    @Autowired
    CountryMapper countryMapper;
    @Autowired
    RedisUtils redisUtils;
    public Country getCountryBycountryId(int countryId){
        return Optional.ofNullable(countryMapper.getCountryByCountryId(countryId)).orElse(new Country());
    }
    public Country getCountryByCountry(String countryName){
        return Optional.ofNullable(countryMapper.getCountryByCountryName(countryName)).orElse(new Country());
    }

    public Country mograteCountryByRedis(int countryId) {
        Country country = countryMapper.getCountryByCountryId(countryId);
        //整数格式化
        String countryKey = String.format("country%d", countryId);
        //入库
        redisUtils.set(countryKey, country);
        return (Country) redisUtils.get(countryKey);
    }
}
