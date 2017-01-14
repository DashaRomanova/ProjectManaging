package code.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Napha on 11.01.2017.
 */
@Entity
@Table(name = "Company")
public class Company {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "Company_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "Company_id")
    private Long companyId;
    @Column(name = "Company_name")
    private String companyName;

    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

    @OneToMany(mappedBy = "company")
    private List<Customer> customers;

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public Company() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
