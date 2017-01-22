package code.domain;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Column(name = "Name", unique = true, nullable = false)
    private Long sprintName;

    @OneToOne()
    @JoinColumn(name="Previous_sprint", referencedColumnName = "Sprint_id", nullable = true)
    private Sprint previousSprint;

    @OneToOne(mappedBy="previousSprint")
    private Sprint parentSprint;

    @OneToMany(mappedBy = "sprint", fetch = FetchType.EAGER)
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

    public Sprint(Long sprintName, Date sprintStartDate, Date sprintFinishDate) {
        this.sprintName = sprintName;
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
    public void addTask(Task task) {
        if(tasks == null){
            tasks = new ArrayList<Task>();
        }
        tasks.add(task);
    }

    public Long getSprintName() {
        return sprintName;
    }

    public void setSprintName(Long sprintName) {
        this.sprintName = sprintName;
    }

    public void setSprintStartDate(Date sprintStartDate) {
        this.sprintStartDate = sprintStartDate;
    }

    public void setSprintFinishDate(Date sprintFinishDate) {
        this.sprintFinishDate = sprintFinishDate;
    }

    public void setPreviousSprint(Sprint previousSprint) {
        this.previousSprint = previousSprint;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
