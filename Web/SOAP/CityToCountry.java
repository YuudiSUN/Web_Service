package travel.management.web.SOAP;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebService(targetNamespace = "http://www.travel.management")
public class CityToCountry {

    private static final Logger LOGGER = Logger.getLogger(CityToCountry.class.getName());

    private static final Map<String, String> cityToCountryMap = new HashMap<>();
    static {
        initializeCityToCountryMap();
    }

    private static void initializeCityToCountryMap() {
        cityToCountryMap.put("New York", "US");
        cityToCountryMap.put("London", "UK");
        cityToCountryMap.put("Paris", "FR");

        LOGGER.info("City to Country map initialized successfully.");
    }

    @WebMethod
    public String getCountryCodeByCityName(@WebParam(name = "cityName") String cityName) {
        String countryCode = cityToCountryMap.get(cityName);
        if (countryCode != null) {
            LOGGER.info("Country code found: " + countryCode + " for city: " + cityName);
            return countryCode;
        } else {
            LOGGER.warning("No country code found for city: " + cityName);
            return "Unknown"; 
        }
    }

    @WebMethod
    public String addCityCountryMapping(@WebParam(name = "cityName") String cityName, @WebParam(name = "countryCode") String countryCode) {
        if (cityToCountryMap.containsKey(cityName)) {
            LOGGER.warning("Mapping already exists for city: " + cityName);
            return "Mapping already exists.";
        } else {
            cityToCountryMap.put(cityName, countryCode);
            LOGGER.info("New mapping added: " + cityName + " -> " + countryCode);
            return "Mapping added successfully.";
        }
    }
}
