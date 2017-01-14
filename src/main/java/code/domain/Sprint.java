package code.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 10.01.2017.
 */
@Entity
@Table(name = "Sprint")
public class Sprint {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "Sprint_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "Sprint_id")
    private Long sprintId;

    @OneToOne()
    @JoinColumn(name="Previous_sprint", referencedColumnName = "Sprint_id", nullable = true)
    private Sprint previousSprint;

    @OneToOne(mappedBy="previousSprint")
    private Sprint parentSprint;

    @OneToMany(mappedBy = "sprint")
    private List<Task> tasks;

    @ManyToOne()
    private Project project;

    @Temporal(TemporalType.DATE)
    @Column(name = "Start_date")
    private Date sprintStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "Finish_date")
    private Date sprintFinishDate;

    public Sprint() {
    }

    public Sprint(Sprint previousSprint, Date sprintStartDate, Date sprintFinishDate) {
        this.previousSprint = previousSprint;
        this.sprintStartDate = sprintStartDate;
        this.sprintFinishDate = sprintFinishDate;
    }

    public Long getSprintId() {
        return sprintId;
    }

    public Sprint getPreviousSprint() {
        return previousSprint;
    }

    public Date getSprintStartDate() {
        return sprintStartDate;
    }

    public Date getSprintFinishDate() {
        return sprintFinishDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
