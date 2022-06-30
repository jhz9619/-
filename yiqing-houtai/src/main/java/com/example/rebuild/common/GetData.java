package com.example.rebuild.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.rebuild.entity.City;
import com.example.rebuild.entity.HttpPojo;
import com.example.rebuild.entity.Province;
import com.example.rebuild.service.Impl.CityServiceImpl;
import com.example.rebuild.service.Impl.ProvinceServiceImpl;
import com.example.rebuild.util.GetDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GetData {

    @Autowired
    ProvinceServiceImpl provinceService;

    @Autowired
    CityServiceImpl cityService;

    @PostConstruct
    public String getAreaData(){

        Province province = new Province();
        City city = new City();

        //清空表数据
        cityService.cancelKey();
        cityService.deleteCity();
        provinceService.deleteProvince();
        cityService.setKey();

        //获取数据的地址
        String url="https://ncov.dxy.cn/ncovh5/view/pneumonia";

        //模拟请求
        HttpPojo httpPojo = new HttpPojo();
        httpPojo.setHttpHost("ncov.dxy.cn");
        httpPojo.setHttpAccept("*/*");
        httpPojo.setHttpConnection("keep-alive");
        httpPojo.setHttpUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        httpPojo.setHttpReferer("https://ncov.dxy.cn");
        httpPojo.setHttpOrigin("https://ncov.dxy.cn");
        Map paramObj = new HashMap();
        String htmlResult = GetDataUtil.httpSendGet(url, paramObj, httpPojo); //整个html页面

        //正则获取数据
        String reg= "window.getAreaStat = (.*?)\\}(?=catch)";
        Pattern totalPattern = Pattern.compile(reg);
        Matcher totalMatcher = totalPattern.matcher(htmlResult);
        String result="";

        if (totalMatcher.find()){

            result = totalMatcher.group(1);
            JSONArray array = JSONArray.parseArray(result);
            int count = 0;

            for(int i = 1; i < array.size(); i++) {

                //各个省市的是一个列表List，遍历结果保存到数据库
                JSONObject jsonObject = JSONObject.parseObject(array.getString(i));

                //获取各个省的基础数据
                /*String proid = jsonObject.getString("locationId");*/

                String proname = jsonObject.getString("provinceName");
                String high = jsonObject.getString("highDangerCount");
                String mid = jsonObject.getString("midDangerCount");
                Integer rznum = Integer.valueOf(high) + Integer.valueOf(mid);
                String panum = jsonObject.getString("currentConfirmedCount");
                String prodead = jsonObject.getString("deadCount");

                province.setProid(i);
                province.setProname(proname);
                province.setRznum(rznum);
                province.setPanum(Integer.parseInt(panum));
                province.setProdead(Integer.parseInt(prodead));
                province.setUpdate_time(LocalDateTime.now());

                provinceService.save(province);

                //获取各个市的基础数据
                JSONArray array2 = jsonObject.getJSONArray("cities");

                for (int j = 1; j < array2.size(); j++){
                    JSONObject jsonObject2 = JSONObject.parseObject(array2.getString(j));

                    /*String cid = jsonObject2.getString("locationId");*/
                    String cname = jsonObject2.getString("cityName");
                    String high2 = jsonObject2.getString("highDangerCount");
                    String mid2 = jsonObject2.getString("midDangerCount");
                    Integer rznum2 = Integer.valueOf(high2) + Integer.valueOf(mid2);
                    String cpanum = jsonObject2.getString("currentConfirmedCount");
                    String citydead = jsonObject2.getString("deadCount");

                    city.setCid(count++);
                    city.setCname(cname);
                    city.setCrznum(rznum2);
                    city.setCpanum(Integer.parseInt(cpanum));
                    city.setProid(i);
                    city.setCitydead(Integer.parseInt(citydead));
                    city.setCityupdate_time(LocalDateTime.now());

                    cityService.save(city);

                }

            }
        }

        return result;
    }
}
