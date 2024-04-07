package travel.management.web.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import travel.management.web.data.Country;

public class CountryService {

    private static Map<Long, Country> COUNTRY_DATA = new HashMap<>();
    private static long currentId = 0;

    private synchronized long getNewId() {
        return ++currentId;
    }

    public Country addCountry(Country country) {
        if (country.getId() != null && COUNTRY_DATA.containsKey(country.getId())) {
            return null;
        }
        long id = getNewId();
        country.setId(id);
        COUNTRY_DATA.put(id, country);
        return country;
    }

    public boolean deleteCountry(Long id) {
        return COUNTRY_DATA.remove(id) != null;
    }

    public Country getCountryById(Long id) {
        return COUNTRY_DATA.get(id);
    }

    public Collection<Country> getAllCountries() {
        return COUNTRY_DATA.values();
    }

    public Country updateCountry(Long id, Country updatedCountry) {
        if (!COUNTRY_DATA.containsKey(id)) {
            return null;
        }
        updatedCountry.setId(id);
        COUNTRY_DATA.put(id, updatedCountry);
        return updatedCountry;
    }
}
