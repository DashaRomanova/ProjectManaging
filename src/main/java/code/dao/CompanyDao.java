package code.dao;

import code.domain.Company;

/**
 * Created by Napha on 11.01.2017.
 */
public interface CompanyDao {
    Long create(Company company);
    Company read(Long id);
    void update(Company company);
    void delete(Company company);
}
