package travel.management.client;

import java.util.List;
import javax.ws.rs.core.*;
import org.apache.cxf.jaxrs.client.WebClient;
import travel.management.web.data.City;
import travel.management.web.data.Country;

import javax.ws.rs.core.Response;

public class TravelAgentClient {

    private static String countryServiceUrl = "http://localhost:8080/travel.management.web/travelApi/countries";
    private static String cityServiceUrl = "http://localhost:8080/travel.management.web/travelApi/cities";

    public static void main(String[] args) {
        // 添加一个国家
        System.out.println("Adding a country...");
        Country country = addCountry("Greece");
        if (country != null) {
            System.out.println("Country added: " + country.getName());
        }

        // 添加城市到国家
        System.out.println("Adding a city to country...");
        City city = addCity("Paris", "Beach",  58.7984,17.8081,country.getId());
        if (city != null) {
            System.out.println("City added: " + city.getName());
        }

        // 获取特定国家的海滩城市
        System.out.println("Getting beach cities for country...");
        List<City> beachCities = getCitiesByType(country.getId(), "Beach");
        beachCities.forEach(c -> System.out.println("Beach city: " + c.getName()));

        // 显示城市天气（需要实现调用天气服务API）
        beachCities.forEach(c -> displayWeather(c.getName(), c.getLatitude(), c.getLongitude()));
    }

    private static Country addCountry(String name) {
        WebClient client = WebClient.create(countryServiceUrl);
        Country newCountry = new Country();
        newCountry.setName(name);
        Response response = client.type(MediaType.APPLICATION_XML).post(newCountry);
        if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
            System.out.println("Failed to add country.");
            return null;
        }
        return response.readEntity(Country.class);
    }
    private static City addCity(String name, String destinationType, double latitude, double longitude, Long countryId) {
        WebClient client = WebClient.create(cityServiceUrl);
        City newCity = new City(destinationType, destinationType, countryId, longitude, longitude);
        newCity.setName(name);
        newCity.setDestinationType(destinationType);
        newCity.setLatitude(latitude); // 设置纬度
        newCity.setLongitude(longitude); // 设置经度
        newCity.setCountryId(countryId);
        Response response = client.type(MediaType.APPLICATION_XML).post(newCity);
        if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
            System.out.println("Failed to add city.");
            return null;
        }
        return response.readEntity(City.class);
    }


    private static List<City> getCitiesByType(Long countryId, String type) {
        WebClient client = WebClient.create(cityServiceUrl).path("/" + countryId);
        client.query("type", type);
        Response response = client.get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            System.out.println("Failed to retrieve cities.");
            return null;
        }
        return response.readEntity(new GenericType<List<City>>(){});
    }

    private static void displayWeather(String cityName, double latitude, double longitude) {
        String weatherData = API.getWeather(longitude, latitude, cityName);
        if (weatherData != null) {
            System.out.println("Weather for " + cityName + " (Lat: " + latitude + ", Long: " + longitude + "): " + weatherData);
        } else {
            System.out.println("Failed to retrieve weather information for " + cityName + " (Lat: " + latitude + ", Long: " + longitude + ")");
        }
    }

}
