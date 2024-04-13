<style>
body {
    font-family: "Times New Roman", Times, serif;
}
</style>
# Web Project Report
### Author
*SUN Yudi* (sunyudi7@gmail.com)

*PAN Wenchong* (wenchong.pan@etu.cyu.fr)

## 1. Introduction
### Don't forget read NOTE
This project aims to develop a web service using Java Dynamic Web Project for managing country and city information for a travel agency, along with integration with external weather services. It includes functionalities for managing country and city information, querying weather information, and implementing unified access and data modification through a client.

### 1.1. Web Services:

### Package: `travel.management.web.data`

- **City.java, Country.java**
  - Attributes: Name, destination type, country ID, longitude, latitude, etc.

### Package: `travel.management.web.resource`

- **CityService.java, CountryService.java**
  - Functions: Provide services related to countries and cities, such as adding, deleting, retrieving, and updating city information.

### Package: `travel.management.web.service`

- **CityResource.java, CountryResource.java**
  - Functions: Define RESTful APIs for country and city resources, including operations like adding, retrieving, updating, and deleting city information.

### 1.2. Client:
The client interacts with the travel management system's RESTful API to manage and query country and city information.

### Package: `travel.management.client`

- **API.java, TravelAgentClient.java**
  - Functions: Add country, add city, get list of cities of specific type, and display weather information for cities.

### 1.3. Code Management:
We use GitHub's distributed version control system for collaboration and code version management.

## 2. Service

### Package: `travel.management.web`

#### Classes:

1. **CityResource**: 
   - Handles HTTP requests related to cities.
   - Important functions:
     - `addCity(City city)`: Adds a new city.
     - `getCitiesByCountry(Long countryId)`: Retrieves cities by country ID.
     - `updateCity(Long cityId, City updatedCity)`: Updates city information.
     - `deleteCity(Long cityId)`: Deletes a city.

2. **CountryResource**:
   - Manages HTTP requests related to countries.
   - Important functions:
     - `addCountry(Country country)`: Adds a new country.
     - `getAllCountries()`: Retrieves all countries.
     - `getCountryById(Long countryId)`: Retrieves a country by ID.
     - `updateCountry(Long countryId, Country updatedCountry)`: Updates country information.
     - `deleteCountry(Long countryId)`: Deletes a country.

### Package: `travel.management.web.data`

#### Classes:

1. **City**:
   - Represents a city with attributes such as name, destination type, longitude, and latitude.
   - Important functions:
     - `City(String name, String destinationType, Long countryId, double longitude, double latitude)`: Constructor for creating a city.
     - Accessor and mutator methods for accessing and modifying city attributes.

2. **Country**:
   - Represents a country with attributes such as name and a set of associated cities.
   - Important functions:
     - `Country(String name)`: Constructor for creating a country.
     - Accessor and mutator methods for accessing and modifying country attributes.

### Package: `travel.management.web.service`

This package contains service classes responsible for managing city and country data.

#### Classes:

1. **CityService**:
   - Provides CRUD operations for managing cities.
   - Important functions:
     - `addCity(City city)`: Adds a new city.
     - `deleteCity(long id)`: Deletes a city by ID.
     - `getCity(long id)`: Retrieves a city by ID.
     - `getAllCities()`: Retrieves all cities.
     - `getCitiesByCountry(long countryId)`: Retrieves cities by country ID.
     - `updateCity(long id, City city)`: Updates city information.

2. **CountryService**:
   - Provides CRUD operations for managing countries.
   - Important functions:
     - `addCountry(Country country)`: Adds a new country.
     - `deleteCountry(Long id)`: Deletes a country by ID.
     - `getCountryById(Long id)`: Retrieves a country by ID.
     - `getAllCountries()`: Retrieves all countries.
     - `updateCountry(Long id, Country updatedCountry)`: Updates country information.


## 3. Client

### Package: `travel.management.client`

#### Classes:

1. **API**:
   - Provides methods for making HTTP requests to external APIs, specifically used for retrieving weather data.
   - Important functions:
     - `getWeather(double lat, double lng, String params)`: Sends a request to the Stormglass API to fetch weather data based on latitude, longitude, and specified parameters.

2. **TravelAgentClient**:
   - Acts as the main client application for interacting with the travel management system.
   - Important functions:
     - `main(String[] args)`: Entry point of the client application. It demonstrates various functionalities such as adding a country, adding a city to a country, retrieving cities by type, and displaying weather information for cities.
     - `addCountry(String name)`: Sends a request to add a new country to the server.
     - `addCity(String name, String destinationType, double latitude, double longitude, Long countryId)`: Sends a request to add a new city to the server associated with a country.
     - `getCitiesByType(Long countryId, String type)`: Sends a request to retrieve cities of a specific type for a given country from the server.
     - `displayWeather(String cityName, double latitude, double longitude)`: Displays weather information for a city by calling the `getWeather` method.




## 4. Demonstration

#### Use Case: Managing Countries and Cities and search cities in special Type

**Steps:**

1. **Start the Server:** Ensure that the server-side application is up and running, assumed to be on port 8080 of localhost.

2. **Run the Client Program:** Execute the `main` method of the `TravelAgentClient` class.

3. **Choose an Operation:** Upon program execution, you'll be prompted to select an operation:
   - Type `search` to find existing cities.
   - Type `add` to add a new city.
   - Type `exit` to quit the program.

4. **Search for Existing Cities:**
   - If you select `search`, input the country name followed by the city type. The program will return a list of cities of the specified type within that country.

5. **Add a New City:**
   - Choosing `add` will guide you through adding a new city. Input the country name, city name, destination type, latitude, and longitude. The program will attempt to add the city to the specified country.

6. **Exit the Program:**
   - Input `exit` to terminate and exit the program.


---
## Note: Don't forget put web.xml !!!!!! SOAP look TP3
### Server Dynamic Web Project
1. put all documents 
2. put web.xml -> run as Tomcat
3. cityToCountry ->WebServer ->Create WebServer
4. src/main/wsdl/citytocountry ->Generate Client ->dovol Clinet ->dont forget add pacakage name:management.travel.client
5. run management.travel.client/cityToCountry_..._Port_Clinet

### Client JAVA Project
1. put all documents 
2. **add SERVER** in enviroment
3.run as JAVA