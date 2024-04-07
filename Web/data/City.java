package travel.management.web.data;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD) // 表明使用字段直接映射
public class City {

    @XmlElement(required = true) // 指示该字段是XML元素的必要字段
    private Long id;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String destinationType;

    @XmlElement(required = true)
    private Long countryId;

    @XmlElement(required = true)
    private Double latitude;

    @XmlElement(required = true)
    private Double longitude;
  
    // 必须有一个无参构造器
    public City() {}
    
    public City(String name, String destinationType, Long countryId, double longitude, double latitude) {
        this.name = name;
        this.destinationType = destinationType;
        this.countryId = countryId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

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

    @Override
    public String toString() {
        return id + "::" + name + "::" + destinationType + "::" + longitude + "::" + latitude;
    }
}
