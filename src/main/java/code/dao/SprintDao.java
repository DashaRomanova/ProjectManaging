package code.dao;

import code.domain.Sprint;

/**
 * Created by Napha on 11.01.2017.
 */
public interface SprintDao {
    Long create(Sprint sprint);
    Sprint read(Long id);
    void update(Sprint sprint);
    void delete(Sprint sprint);
}
