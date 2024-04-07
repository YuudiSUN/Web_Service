package travel.management.web.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class City {

    private Long id;
    private String name;
    private String destinationType; // 如：“海滩”，“山区”
    private Long countryId; // 保存关联的Country ID

    public City() {}

    public City(String name, String destinationType, Long countryId) {
        this.name = name;
        this.destinationType = destinationType;
        this.countryId = countryId;
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

    @Override
    public String toString() {
        return id + "::" + name + "::" + destinationType;
    }
}
