package code.dao;

import code.domain.Status;

/**
 * Created by Napha on 11.01.2017.
 */
public interface StatusDao {
    Long create(Status status);
    Status read(Long id);
    void update(Status status);
    void delete(Status status);
    Status getStatusByName(String name);
}
