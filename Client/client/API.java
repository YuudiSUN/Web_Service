package travel.management.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;
//API调用格式：lat+lng+天气类型
public class API {

    public static String getWeather(double lat, double lng, String params) {
        // Stormglass API endpoint
        String apiUrl = "https://api.stormglass.io/v2/weather/point";

        // API key - replace with your actual API key
        String apiKey = "e6fd4d3e-f1c6-11ee-a7bb-0242ac130002-e6fd4db6-f1c6-11ee-a7bb-0242ac130002";
        params = "airTemperature,windSpeed";
        // Constructing the request URL
        String requestUrl = apiUrl + "?lat=" + lat + "&lng=" + lng + "&params=" + params;

        try {
            // Creating HTTP connection
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Setting request properties
            connection.setRequestMethod("GET");
            // Ensure there's no leading or trailing whitespace in the API key
            connection.setRequestProperty("Authorization", apiKey.trim());

            // Checking for successful response code
            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                System.out.println("Error: Server returned HTTP response code: " + status + " for URL: " + requestUrl);
                return null;
            }

            // Reading response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = in.lines().collect(Collectors.joining());

            // Closing resources
            in.close();
            connection.disconnect();

            // Returning the weather information
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to retrieve weather information. Error: " + e.getMessage();
        }
    }
}
