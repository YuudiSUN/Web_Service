package travel.management.web.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import travel.management.web.data.Country;

public class CountryService {

    // A map to store country data, with country ID as the key and Country object as the value
    private static Map<Long, Country> COUNTRY_DATA = new HashMap<>();
    // A counter to generate unique IDs for countries
    private static long currentId = 0;

    // Synchronized method to ensure thread-safe incrementation and retrieval of the next country ID
    private synchronized long getNewId() {
        return ++currentId;
    }

    // Adds a new country to the store. Returns null if the country already exists.
    public Country addCountry(Country country) {
        // Check if the country ID is already in use. If so, return null.
        if (country.getId() != null && COUNTRY_DATA.containsKey(country.getId())) {
            return null;
        }
        // Generate a new ID for the country
        long id = getNewId();
        country.setId(id); // Set the country's ID
        COUNTRY_DATA.put(id, country); // Store the country in the map
        return country; // Return the newly added country
    }

    // Deletes a country by its ID and returns true if the operation was successful
    public boolean deleteCountry(Long id) {
        // Remove the country from the map. Returns true if the country was found and removed.
        return COUNTRY_DATA.remove(id) != null;
    }

    // Retrieves a country by its ID. Returns null if the country does not exist.
    public Country getCountryById(Long id) {
        // Return the country with the specified ID, or null if it does not exist
        return COUNTRY_DATA.get(id);
    }

    // Returns a collection of all countries stored in the service
    public Collection<Country> getAllCountries() {
        // Return all country values from the map
        return COUNTRY_DATA.values();
    }

    // Updates the information of an existing country. Returns null if the country does not exist.
    public Country updateCountry(Long id, Country updatedCountry) {
        // Check if the country exists
        if (!COUNTRY_DATA.containsKey(id)) {
            return null; // Return null if the country does not exist
        }
        updatedCountry.setId(id); // Ensure the updated country's ID matches the original
        COUNTRY_DATA.put(id, updatedCountry); // Replace the old country data with the updated data
        return updatedCountry; // Return the updated country
    }
}
