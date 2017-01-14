package code.dao;

import code.domain.Role;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Napha on 11.01.2017.
 */
@Repository
public class RoleDaoImpl implements RoleDao {
    @Autowired(required = true)
    private SessionFactory factory;

    public RoleDaoImpl() {
    }

    public Long create(Role role) {
        return (Long)factory.getCurrentSession().save(role);
    }

    public Role read(Long id) {
        return factory.getCurrentSession().get(Role.class, id);
    }

    public void update(Role role) {
        factory.getCurrentSession().update(role);
    }

    public void delete(Role role) {
        factory.getCurrentSession().delete(role);
    }

    @Transactional
    public Role getRoleByName(String name) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Role r where r.roleName =:roleName");
        query.setParameter("roleName", name);
        return (Role)query.uniqueResult();
    }
}
