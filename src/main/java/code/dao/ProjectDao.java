package code.dao;

import code.domain.Project;

/**
 * Created by Napha on 11.01.2017.
 */
public interface ProjectDao {
    Long create(Project project);
    Project read(Long id);
    void update(Project project);
    void delete(Project project);
}
