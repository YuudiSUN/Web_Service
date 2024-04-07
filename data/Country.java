package travel.management.web.data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set; // 你可能需要为城市列表添加适当的处理

@XmlRootElement
public class Country {

    private Long id;
    private String name;
    private Set<City> cities; // 注意：对于实际的XML/JSON序列化，你可能需要处理一对多关系的展示

    public Country() {}

    public Country(String name) {
        this.name = name;
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

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return id + "::" + name;
    }
}
