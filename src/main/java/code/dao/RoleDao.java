package code.dao;

import code.domain.Role;

/**
 * Created by Napha on 11.01.2017.
 */
public interface RoleDao {
    Long create(Role role);
    Role read(Long id);
    void update(Role role);
    void delete(Role role);

    Role getRoleByName(String name);
}
