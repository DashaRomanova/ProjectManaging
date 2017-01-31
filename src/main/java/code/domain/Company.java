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
    @SequenceGenerator(name = "sequence", sequenceName = "Company_SEQ", allocationSize = 100, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "Company_id")
    private Long companyId;
    @Column(name = "Company_name")
    private String companyName;
    @Column(name = "Phone_Number")
    private String phoneNumber;
    @Column(name = "Address")
    private String address;


    public Company() {
    }

    public Company(String companyName, String phoneNumber, String address) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
