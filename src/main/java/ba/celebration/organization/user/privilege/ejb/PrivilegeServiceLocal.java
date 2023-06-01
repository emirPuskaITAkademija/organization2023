package ba.celebration.organization.user.privilege.ejb;

import jakarta.ejb.Local;

import java.util.List;

@Local
public interface PrivilegeServiceLocal {

    void create(Privilege privilege);

    void remove(Privilege privilege);

    Privilege find(Object id);

    List<Privilege> findAll();
}
