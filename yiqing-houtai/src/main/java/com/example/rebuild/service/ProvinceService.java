package com.example.rebuild.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rebuild.entity.Province;

public interface ProvinceService extends IService<Province> {

    void deleteProvince();

}

