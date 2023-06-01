package ba.celebration.organization.country.town;

import ba.celebration.organization.service.AbstractService;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class TownService extends AbstractService<Town> implements TownServiceLocal {
    @PersistenceContext(name = "birthdayPU")
    private EntityManager entityManager;
    public TownService() {
        super(Town.class);
    }

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }
}
