package code.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Napha on 10.01.2017.
 */
@Entity
@Table(name = "Qualification")
public class Qualification {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "Qualification_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "Qualification_id")
    private Long qualificationId;
    @Column(name = "Qualification_name", unique = true, nullable = false)
    private String qualificationName;
    @OneToMany(mappedBy = "qualification")
    private List<Employee> employees;

    @OneToMany(mappedBy = "taskQualification")
    private List<Task> tasks;

    public Qualification() {
    }

    public Qualification(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public Long getQualificationId() {
        return qualificationId;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }
}