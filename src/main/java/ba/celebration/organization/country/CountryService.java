package ba.celebration.organization.country;

import ba.celebration.organization.service.AbstractService;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class CountryService extends AbstractService<Country> implements CountryServiceLocal {
    @PersistenceContext(name = "birthdayPU")
    private EntityManager entityManager;

    public CountryService() {
        super(Country.class);
    }

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }
}
