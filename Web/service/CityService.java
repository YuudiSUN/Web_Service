package travel.management.web.service;

import java.util.*;
import travel.management.web.data.City;

public class CityService {

  private static Map<Long, City> CITY_DATA = new HashMap<>();
  private static long currentId = 0;

  private synchronized long getNewId() {
    return ++currentId;
  }
  
  public City addCity(City city) {
	    long id = getNewId();
	    city.setId(id);
	    // 假设City对象已经在构造时设置了经度和纬度
	    CITY_DATA.put(id, city);
	    return city;
	}

  public boolean deleteCity(long id) {
    if (!CITY_DATA.containsKey(id)) {
      return false;
    }
    CITY_DATA.remove(id);
    return true;
  }

  public City getCity(long id) {
    return CITY_DATA.get(id);
  }

  public Collection<City> getAllCities() {
    return CITY_DATA.values();
  }

  public Collection<City> getCitiesByCountry(long countryId) {
    List<City> cities = new ArrayList<>();
    for (City city : CITY_DATA.values()) {
      if (city.getCountryId() != null && city.getCountryId().equals(countryId)) {
        cities.add(city);
      }
    }
    return cities;
  }

  public City updateCity(long id, City city) {
    if (!CITY_DATA.containsKey(id)) {
      return null;
    }
    city.setId(id);
    CITY_DATA.put(id, city);
    return city;
  }
  
  // 添加经度和纬度支持
  public City updateCityWithCoordinates(long id, double longitude, double latitude) {
    if (!CITY_DATA.containsKey(id)) {
      return null;
    }
    City city = CITY_DATA.get(id);
    city.setLongitude(longitude);
    city.setLatitude(latitude);
    CITY_DATA.put(id, city);
    return city;
  }
}
