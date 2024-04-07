package travel.management.web.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TravelAgentService {
    // 用于存储国家及其对应的城市和目的地类型的映射关系
    private Map<String, Set<City>> countryCitiesMap;

    public TravelAgentService() {
        // 初始化国家城市映射关系
        countryCitiesMap = new HashMap<>();
    }

    // 添加国家
    public void addCountry(String countryName) {
        countryCitiesMap.put(countryName, new HashSet<>());
    }

    // 添加城市到指定国家
    public void addCityToCountry(String countryName, String cityName, String destinationType) {
        Set<City> cities = countryCitiesMap.get(countryName);
        if (cities != null) {
            cities.add(new City(cityName, destinationType));
        } else {
            System.out.println("国家不存在！");
        }
    }

    // 按目的地类型检索城市
    public Set<City> getCitiesByDestinationType(String destinationType) {
        Set<City> citiesByType = new HashSet<>();
        for (Set<City> cities : countryCitiesMap.values()) {
            for (City city : cities) {
                if (city.getDestinationType().equals(destinationType)) {
                    citiesByType.add(city);
                }
            }
        }
        return citiesByType;
    }

    // 内部类表示城市
    private class City {
        private String name;
        private String destinationType;

        public City(String name, String destinationType) {
            this.name = name;
            this.destinationType = destinationType;
        }

        public String getName() {
            return name;
        }

        public String getDestinationType() {
            return destinationType;
        }
    }
}
