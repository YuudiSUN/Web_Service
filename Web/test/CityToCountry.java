package test;

import javax.jws.WebService;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(targetNamespace = "http://www.travel.management")
public class CityToCountry {

    // 假设这里是您城市到国家代码的映射
    private static final Map<String, String> cityToCountryMap = new HashMap<>();
    static {
        cityToCountryMap.put("New York", "US");
        cityToCountryMap.put("London", "UK");
        cityToCountryMap.put("Paris", "FR");
        // 添加更多城市和国家代码映射
    }

    @WebMethod
    public String getCountryCodeByCityName(@WebParam(name = "cityName") String cityName) {
        String countryCode = cityToCountryMap.get(cityName);
        if (countryCode != null) {
            return countryCode;
        } else {
            return "Unknown"; // 如果找不到城市对应的国家代码，返回 "Unknown"
        }
    }
}
