package ba.celebration.organization.user.ejb;

import jakarta.ejb.Local;

import java.util.List;

@Local
public interface UserServiceLocal {

    void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);

    List<User> findAll();

    User findByUsername(String username);
}
