package code.dao;

import code.domain.Qualification;

import java.util.List;

/**
 * Created by Napha on 11.01.2017.
 */
public interface QualificationDao {
    Long create(Qualification qualification);
    Qualification read(Long id);
    void update(Qualification qualification);
    void delete(Qualification qualification);
    List getAllQualifications();
}
