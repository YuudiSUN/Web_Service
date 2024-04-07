
# Web项目报告
### Author
SUN Yudi sunyudi7@gmail.com

PAN Wenchong wenchong.pan@etu.cyu.fr

## 1. Introduction

本项目旨在开发一个基于Java Dynamic Web Projet的Web服务端和客户端，用于管理旅行代理商的国家和城市信息，并提供与外部天气服务的集成功能。包括国家和城市信息管理、天气查询功能，并通过客户端实现统一的访问和数据修改。
### 1.1. Web Services :

### Package: `travel.management.web.data`

- **City.java，Country.java**
  - 属性：名称、目的地类型、所属国家ID、经度和纬度等。

### Package: `travel.management.web.resource`

- **CityService.java，CountryService.java**
  - 功能：提供国家和城市相关服务，如添加、删除、获取和更新城市信息。

### Package: `travel.management.web.service`

- **CityResource.java，CountryResource.java**
  - 功能：定义国家和城市资源的 RESTful API，包括添加、获取、更新和删除城市信息等操作。

### 1.2. Client :
客户端通过调用旅行管理系统的 RESTful API，实现对国家和城市信息的管理和查询。
### Package: `travel.management.client`

- **API.java，TravelAgentClient.java**
  - 功能：添加国家、添加城市、获取特定类型的城市列表以及显示城市的天气信息。

### 1.3. Code Mode：
我们采用GitHub的分布式版本控制系统，来进行多人协作和代码版本查询。

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




## 4. 演示

#### 用例名称: 添加国家和城市，查询制定类型城市

**步骤:**
1. 首先，客户端添加一个名为 "France" 的国家。
2. 然后，客户端添加一个名为 "Marseille" 的城市，将其类型设置为 "Beach"，并将其关联到 "France" 国家。
3. 接下来，客户端获取 "France" 国家中所有类型为 "Beach" 的城市列表。
4. 最后，客户端显示每个海滩城市的天气信息。

**结果:**
- 国家 "France" 和城市 "Marseille" 被成功添加到系统中。
- 获取到的海滩城市列表包含了 "Marseille"。
- 每个海滩城市都成功显示了天气信息。


---
*注意: 本报告提供了该项目及其组件的高级概述。有关详细的技术文档，请参阅项目文档。*