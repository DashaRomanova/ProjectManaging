package code.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Napha on 10.01.2017.
 */

@Entity
@Table(name = "Status")
public class Status {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "Status_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "Status_id")
    private Long statusId;
    @Column(name = "Name", unique = true, nullable = false)
    private String statusName;
    @OneToMany(mappedBy = "status")
    private List<Task> tasks;


    public Status() {
    }

    public Status(String statusName) {
        this.statusName = statusName;
    }

    public Long getStatusId() {
        return statusId;
    }

    public String getStatusName() {
        return statusName;
    }
}
