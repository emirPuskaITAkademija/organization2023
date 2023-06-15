package ba.celebration.organization.country.town;

import jakarta.ejb.Local;

import java.util.List;

@Local
public interface TownServiceLocal {
    void create(Town town);

    void edit(Town town);

    void remove(Town town);

    void remove(Long id);

    Town find(Object id);

    List<Town> findAll();

    List<Town> findPage(int page, int pageSize);

    int count();
}
