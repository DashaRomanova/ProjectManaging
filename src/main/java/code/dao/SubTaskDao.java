package code.dao;

import code.domain.SubTask;

/**
 * Created by Napha on 14.01.2017.
 */
public interface SubTaskDao {
    Long create(SubTask subTask);
    SubTask read(Long id);
    void update(SubTask subTask);
    void delete(SubTask subTask);

    SubTask findTaskByName(String name);
}
