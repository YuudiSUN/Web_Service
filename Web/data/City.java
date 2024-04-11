package travel.management.web.data;

import javax.xml.bind.annotation.*;

// Declares this class as an XML root element, which allows it to be converted to and from XML
@XmlRootElement
// Specifies that the fields of the class are to be automatically bound to XML elements
@XmlAccessorType(XmlAccessType.FIELD)
public class City {

    // Marks this field as an XML element, indicating it must be present in the XML document
    @XmlElement(required = true)
    private Long id;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String destinationType;

    @XmlElement(required = true)
    private Long countryId;   // To link with country, indicating which country the city belongs to

    @XmlElement(required = true)
    private Double latitude;

    @XmlElement(required = true)
    private Double longitude;

    // No-argument constructor required for XML binding
    public City() {}

    // Constructor to initialize a city with its name, destination type, country ID, longitude, and latitude
    public City(String name, String destinationType, Long countryId, double longitude, double latitude) {
        this.name = name;
        this.destinationType = destinationType;
        this.countryId = countryId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // Getter and setter methods for all fields, allowing for retrieval and updating of the city's attributes
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(String destinationType) {
        this.destinationType = destinationType;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // Override the toString method to provide a string representation of the city object
    @Override
    public String toString() {
        return id + "::" + name + "::" + destinationType + "::" + longitude + "::" + latitude;
    }
}
