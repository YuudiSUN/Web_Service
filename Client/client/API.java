package travel.management.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

// Class for making API calls to retrieve weather information
public class API {

    // Method to fetch weather information using latitude, longitude, and parameters
    public static String getWeather(double lat, double lng, String params) {
        // Base URL for the Stormglass API endpoint
        String apiUrl = "https://api.stormglass.io/v2/weather/point";

        // Placeholder for the API key - this should be replaced with an actual API key
        String apiKey = "e6fd4d3e-f1c6-11ee-a7bb-0242ac130002-e6fd4db6-f1c6-11ee-a7bb-0242ac130002";
        // Default parameters for the weather data request (e.g., air temperature and wind speed)
        params = "airTemperature,windSpeed";
        // Constructing the full URL for the API request with the given parameters
        String requestUrl = apiUrl + "?lat=" + lat + "&lng=" + lng + "&params=" + params;

        try {
            // Creating a URL object from the request URL
            URL url = new URL(requestUrl);
            // Opening a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Setting the request method to GET
            connection.setRequestMethod("GET");
            // Adding the API key to the request header, ensuring no leading/trailing whitespace
            connection.setRequestProperty("Authorization", apiKey.trim());

            // Getting the response code to check if the request was successful
            int status = connection.getResponseCode();
            // If the response code is not OK, print an error message
            if (status != HttpURLConnection.HTTP_OK) {
                System.out.println("Error: Server returned HTTP response code: " + status + " for URL: " + requestUrl);
                return null;
            }

            // Reading the response from the input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            // Collecting the response lines into a single String
            String response = in.lines().collect(Collectors.joining());

            // Closing the BufferedReader
            in.close();
            // Disconnecting the HttpURLConnection
            connection.disconnect();

            // Returning the weather information as a String
            return response;
        } catch (IOException e) {
            // Printing the stack trace in case of an IOException
            e.printStackTrace();
            // Returning an error message if unable to retrieve weather information
            return "Failed to retrieve weather information. Error: " + e.getMessage();
        }
    }
}
