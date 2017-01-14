package code.dao;

import code.domain.Task;

/**
 * Created by Napha on 14.01.2017.
 */
public interface TaskDao {
    Long create(Task task);
    Task read(Long id);
    void update(Task task);
    void delete(Task task);

    Task findTaskByName(String name);
}
