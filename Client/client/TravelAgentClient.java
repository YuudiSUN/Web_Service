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
            System.out.println("Enter 'search' to find existing cities or 'add' to add a new city. Enter 'exit' to quit.");
            userInput = scanner.nextLine().trim();

            if ("search".equalsIgnoreCase(userInput)) {
                searchCities(scanner);
            } else if ("add".equalsIgnoreCase(userInput)) {
                addCity(scanner);
            }

        } while (!"exit".equalsIgnoreCase(userInput));

        scanner.close();
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
            if (filteredCities != null) {
                System.out.println("Cities of type " + cityType + ":");
                filteredCities.forEach(c -> System.out.println(c.getName()));
            } else {
                System.out.println("No cities found for the specified type.");
            }
        } else {
            System.out.println("Country not found.");
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
        String responseContent = response.readEntity(String.class); // 读取实体内容
        System.out.println("Response content: " + responseContent);
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            // 从响应内容中手动提取所需信息
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