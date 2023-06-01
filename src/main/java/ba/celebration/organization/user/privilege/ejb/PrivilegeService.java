package ba.celebration.organization.user.privilege.ejb;

import ba.celebration.organization.service.AbstractService;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class PrivilegeService extends AbstractService<Privilege> implements PrivilegeServiceLocal {

    @PersistenceContext(name = "birthdayPU")
    private EntityManager entityManager;

    public PrivilegeService() {
        super(Privilege.class);
    }

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }
}
