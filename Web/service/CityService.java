package travel.management.web.service;

import java.util.*;
import travel.management.web.data.City;

public class CityService {

  // A map to store city data with city ID as the key and City object as the value
  private static Map<Long, City> CITY_DATA = new HashMap<>();
  // A counter to generate unique IDs for cities
  private static long currentId = 0;

  // Synchronized method to ensure thread-safe incrementation and retrieval of the next city ID
  private synchronized long getNewId() {
    return ++currentId;
  }
  
  // Adds a new city to the store and returns it
  public City addCity(City city) {
	    long id = getNewId(); // Generate a new ID for the city
	    city.setId(id); // Set the city's ID
	    CITY_DATA.put(id, city); // Store the city in the map
	    return city; // Return the newly added city
	}

  // Deletes a city by its ID and returns true if successful, false otherwise
  public boolean deleteCity(long id) {
    if (!CITY_DATA.containsKey(id)) { // Check if the city exists
      return false; // Return false if city does not exist
    }
    CITY_DATA.remove(id); // Remove the city from the map
    return true; // Return true upon successful deletion
  }

  // Retrieves a city by its ID and returns it
  public City getCity(long id) {
    return CITY_DATA.get(id); // Return the city with the specified ID
  }

  // Returns a collection of all cities in the store
  public Collection<City> getAllCities() {
    return CITY_DATA.values(); // Return all city values from the map
  }

  // Retrieves and returns a collection of cities belonging to a specific country
  public Collection<City> getCitiesByCountry(long countryId) {
    List<City> cities = new ArrayList<>();
    for (City city : CITY_DATA.values()) { // Loop through all cities
      if (city.getCountryId() != null && city.getCountryId().equals(countryId)) { // Check if the city belongs to the specified country
        cities.add(city); // Add city to the list
      }
    }
    return cities; // Return the list of cities
  }

  // Updates a city's information by its ID and returns the updated city
  public City updateCity(long id, City city) {
    if (!CITY_DATA.containsKey(id)) { // Check if the city exists
      return null; // Return null if city does not exist
    }
    city.setId(id); // Ensure the city's ID is correct
    CITY_DATA.put(id, city); // Update the city information in the map
    return city; // Return the updated city
  }
  
  // Updates the coordinates of an existing city by its ID and returns the updated city
  public City updateCityWithCoordinates(long id, double longitude, double latitude) {
    if (!CITY_DATA.containsKey(id)) { // Check if the city exists
      return null; // Return null if city does not exist
    }
    City city = CITY_DATA.get(id); // Retrieve the existing city
    city.setLongitude(longitude); // Update the longitude
    city.setLatitude(latitude); // Update the latitude
    CITY_DATA.put(id, city); // Store the updated city in the map
    return city; // Return the updated city
  }
}
