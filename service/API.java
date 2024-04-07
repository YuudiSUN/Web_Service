package travel.management.web.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {
    
    public static String getWeather(String city) {
        // Stormglass API endpoint
        String apiUrl = "https://api.stormglass.io/v2/weather";

        // API key - replace with your actual API key
        String apiKey = "e6fd4d3e-f1c6-11ee-a7bb-0242ac130002-e6fd4db6-f1c6-11ee-a7bb-0242ac130002";

        // Constructing the request URL
        String requestUrl = apiUrl + "?query=" + city;

        try {
            // Creating HTTP connection
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Setting request properties
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", apiKey);

            // Reading response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            // Returning the weather information
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
