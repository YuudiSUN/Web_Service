package travel.management.web.data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set; // Importing Set for managing collections of cities

// Declares this class as an XML root element, enabling its instances to be converted to and from XML
@XmlRootElement
public class Country {

    private Long id; // Unique identifier for each country
    private String name; // Name of the country
    private Set<City> cities; // A collection of City objects associated with this country

    // Default constructor required by JAXB for XML binding
    public Country() {}

    // Constructor that initializes a country with its name
    public Country(String name) {
        this.name = name;
    }

    // Getter for the country ID
    public Long getId() {
        return id;
    }

    // Setter for the country ID
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for the country name
    public String getName() {
        return name;
    }

    // Setter for the country name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the set of cities associated with the country
    public Set<City> getCities() {
        return cities;
    }

    // Setter for assigning a set of cities to the country
    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    // Override the toString method to provide a string representation of the country object
    @Override
    public String toString() {
        return id + "::" + name;
    }
}
