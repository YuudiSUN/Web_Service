package travel.management.client;

import java.util.List;
import javax.ws.rs.core.*;
import org.apache.cxf.jaxrs.client.WebClient;
import travel.management.web.data.City;
import travel.management.web.data.Country;

import javax.ws.rs.core.Response;

public class TravelAgentClient {

    // URLs to the web services for country and city management
    private static String countryServiceUrl = "http://localhost:8080/travel.management.web/travelApi/countries";
    private static String cityServiceUrl = "http://localhost:8080/travel.management.web/travelApi/cities";

    public static void main(String[] args) {
        // Adding a new country
        System.out.println("Adding a country...");
        Country country = addCountry("Greece");
        if (country != null) {
            System.out.println("Country added: " + country.getName());
        }

        // Adding a city to the previously added country
        System.out.println("Adding a city to country...");
        City city = addCity("Paris", "Beach",  58.7984,17.8081,country.getId());
        if (city != null) {
            System.out.println("City added: " + city.getName());
        }

        // Retrieving beach cities for a specific country
        System.out.println("Getting beach cities for country...");
        List<City> beachCities = getCitiesByType(country.getId(), "Beach");
        beachCities.forEach(c -> System.out.println("Beach city: " + c.getName()));

        // Displaying weather for each beach city (Implementation of weather service API call is needed)
        beachCities.forEach(c -> displayWeather(c.getName(), c.getLatitude(), c.getLongitude()));
    }

    // Method to add a new country using the web service
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

    // Method to add a new city to a country
    private static City addCity(String name, String destinationType, double latitude, double longitude, Long countryId) {
        WebClient client = WebClient.create(cityServiceUrl);
        City newCity = new City(destinationType, name, countryId, longitude, latitude);
        newCity.setName(name);
        newCity.setDestinationType(destinationType);
        newCity.setLatitude(latitude); // Setting latitude
        newCity.setLongitude(longitude); // Setting longitude
        newCity.setCountryId(countryId);
        Response response = client.type(MediaType.APPLICATION_XML).post(newCity);
        if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
            System.out.println("Failed to add city.");
            return null;
        }
        return response.readEntity(City.class);
    }

    // Method to retrieve cities of a specific type for a country
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

    // Method to display weather information for a city
    private static void displayWeather(String cityName, double latitude, double longitude) {
        // The API.getWeather method needs to be implemented to make this work
        String weatherData = API.getWeather(latitude, longitude, cityName);
        if (weatherData != null) {
            System.out.println("Weather for " + cityName + " (Lat: " + latitude + ", Long: " + longitude + "): " + weatherData);
        } else {
            System.out.println("Failed to retrieve weather information for " + cityName + " (Lat: " + latitude + ", Long: " + longitude + ")");
        }
    }
}
