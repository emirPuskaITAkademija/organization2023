package ba.celebration.organization.country;

import ba.celebration.organization.country.town.Town;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "country")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    private String name;

    @OneToMany(mappedBy = "country")
    private List<Town> townList;

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

    public List<Town> getTownList() {
        return townList;
    }

    public void setTownList(List<Town> townList) {
        this.townList = townList;
    }
}
