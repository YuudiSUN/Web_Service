package travel.management.client;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.core.*;
import org.apache.cxf.jaxrs.client.WebClient;
import travel.management.web.data.City;
import travel.management.web.data.Country;

import javax.ws.rs.core.Response;

public class TravelAgentClient {

    private static String countryServiceUrl = "http://localhost:8080/travel.management.web/travelApi/countries";
    private static String cityServiceUrl = "http://localhost:8080/travel.management.web/travelApi/cities";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            System.out.println("Enter 'search' to find existing cities, 'add' to add a new city, or 'check' to check the weather, or 'watch' to watch all cities. Enter 'exit' to quit.");
            userInput = scanner.nextLine().trim();

            if ("search".equalsIgnoreCase(userInput)) {
                searchCities(scanner);
            } else if ("add".equalsIgnoreCase(userInput)) {
                addCity(scanner);
            } else if ("check".equalsIgnoreCase(userInput)) {
                checkWeather(scanner);
            }else if ("watch".equalsIgnoreCase(userInput)) {
                watchAllCities();
            }


        } while (!"exit".equalsIgnoreCase(userInput));

        scanner.close();
    }
    private static void checkWeather(Scanner scanner) {
        System.out.println("Please enter the city name:");
        String cityName = scanner.nextLine().trim();

        // Get city details by name
        City city = getCityByName(cityName);

        if (city != null) {
            double latitude = city.getLatitude();
            double longitude = city.getLongitude();

            System.out.println("Fetching weather information for " + cityName + "...");

            // Default parameters for the weather data request
            String params = "airTemperature,windSpeed";
            String weatherInfo = API.getWeather(latitude, longitude, params);

            if (weatherInfo != null) {
                System.out.println("Weather information for " + cityName + ":");
                System.out.println(weatherInfo);
            } else {
                System.out.println("Failed to retrieve weather information.");
            }
        } else {
            System.out.println("City not found.");
        }
    }

    private static City getCityByName(String cityName) {
        // Make a request to the city service to retrieve city details by name
        WebClient client = WebClient.create(cityServiceUrl);
        Response response = client.query("name", cityName).get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            // Assuming the response contains city details in XML format
            return response.readEntity(City.class);
        } else {
            return null;
        }
    }

    private static void watchAllCities() {
        WebClient client = WebClient.create(cityServiceUrl);
        Response response = client.get();
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            List<City> cities = response.readEntity(new GenericType<List<City>>() {});
            if (cities != null && !cities.isEmpty()) {
                System.out.println("All Cities:");
                cities.forEach(city -> System.out.println(city.getName()));
            } else {
                System.out.println("No cities found.");
            }
        } else {
            System.out.println("Failed to retrieve cities.");
        }
    }


    private static void searchCities(Scanner scanner) {
        System.out.println("Please enter the country name:");
        String countryName = scanner.nextLine().trim();

        // Assuming the country already exists, no error handling for simplicity
        Long countryId = getCountryIdByName(countryName);

        if (countryId != null) {
            System.out.println("Please enter the city type:");
            String cityType = scanner.nextLine().trim();

            List<City> filteredCities = getCitiesByType(countryId, cityType);
            if (filteredCities != null && !filteredCities.isEmpty()) {
                System.out.println("Cities of type " + cityType + ":");
                for (City city : filteredCities) {
                    System.out.println(city.getName());
                    checkWeatherForCity(city);
                }
            } else {
                System.out.println("No cities found for the specified type.");
            }
        } else {
            System.out.println("Country not found.");
        }
    }

    private static void checkWeatherForCity(City city) {
        double latitude = city.getLatitude();
        double longitude = city.getLongitude();

        System.out.println("Fetching weather information for " + city.getName() + "...");

        // Default parameters for the weather data request
        String params = "airTemperature,windSpeed";
        String weatherInfo = API.getWeather(latitude, longitude, params);

        if (weatherInfo != null) {
            System.out.println("Weather information for " + city.getName() + ":");
            System.out.println(weatherInfo);
        } else {
            System.out.println("Failed to retrieve weather information for " + city.getName() + ".");
        }
    }


    private static void addCity(Scanner scanner) {
        // Add country
        System.out.println("Please enter the country name:");
        String countryName = scanner.nextLine().trim();
        Country country = addCountry(countryName);

        if (country != null) {
            // Add city to country
            System.out.println("Please enter the city name:");
            String cityName = scanner.nextLine().trim();
            System.out.println("Please enter the city's destination type:");
            String destinationType = scanner.nextLine().trim();
            System.out.println("Please enter the city's latitude:");
            double latitude = scanner.nextDouble();
            System.out.println("Please enter the city's longitude:");
            double longitude = scanner.nextDouble();

            City city = addCity(cityName, destinationType, latitude, longitude, country.getId());
            if (city != null) {
                System.out.println("City added: " + city.getName());
            }
        } else {
            System.out.println("Failed to add country.");
        }
    }

    private static Long getCountryIdByName(String name) {
        WebClient client = WebClient.create(countryServiceUrl);
        Response response = client.query("name", name).get();
        System.out.println("Response status: " + response.getStatus());
        String responseContent = response.readEntity(String.class); 
        System.out.println("Response content: " + responseContent);
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            // get what we need by hands
            int startIndex = responseContent.indexOf("<id>");
            int endIndex = responseContent.indexOf("</id>");
            if (startIndex != -1 && endIndex != -1) {
                String idString = responseContent.substring(startIndex + 4, endIndex);
                Long countryId = Long.parseLong(idString);
                System.out.println("Country ID: " + countryId);
                return countryId;
            } else {
                System.out.println("No country found.");
                return null;
            }
        } else {
            System.out.println("Failed to retrieve country id.");
            return null;
        }
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
        newCity.setLatitude(latitude);
        newCity.setLongitude(longitude);
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
        return response.readEntity(new GenericType<List<City>>() {});
    }
}