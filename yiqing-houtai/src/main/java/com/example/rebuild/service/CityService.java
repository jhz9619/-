package com.example.rebuild.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rebuild.entity.City;

public interface CityService extends IService<City> {

    void deleteCity();

    void setKey();

    void cancelKey();

}