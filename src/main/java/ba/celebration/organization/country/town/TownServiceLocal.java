package ba.celebration.organization.country.town;

import jakarta.ejb.Local;

import java.util.List;

@Local
public interface TownServiceLocal {
    void create(Town town);

    void remove(Town town);

    Town find(Object id);

    List<Town> findAll();
}
