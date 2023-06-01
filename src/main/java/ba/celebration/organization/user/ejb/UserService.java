package ba.celebration.organization.user.ejb;

import ba.celebration.organization.service.AbstractService;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UserService extends AbstractService<User> implements UserServiceLocal{
    @PersistenceContext(unitName = "birthdayPU")
    private EntityManager entityManager;

    public UserService() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        try {
            Query query = entityManager.createNamedQuery("User.findByUsername");
            query.setParameter("username", username);
            return (User) query.getSingleResult();
        }catch (Exception exception){
            return null;
        }
    }

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }
}
