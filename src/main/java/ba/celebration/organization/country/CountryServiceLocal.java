package ba.celebration.organization.country;


import ba.celebration.organization.country.Country;
import ba.celebration.organization.user.privilege.ejb.Privilege;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface CountryServiceLocal {
    void create(Country country);

    void remove(Country country);

    Country find(Object id);

    List<Country> findAll();
}
