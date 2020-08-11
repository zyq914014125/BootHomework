package com.home.module.home1.Service;

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

    public Country getCountryBycountryId(int countryId){
        return Optional.ofNullable(countryMapper.getCountryByCountryId(countryId)).orElse(new Country());
    }
    public Country getCountryByCountry(String countryName){
        return Optional.ofNullable(countryMapper.getCountryByCountryName(countryName)).orElse(new Country());
    }
}
