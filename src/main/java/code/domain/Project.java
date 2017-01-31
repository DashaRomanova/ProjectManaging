package code.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 11.01.2017.
 */

@Entity
@Table(name = "Project")
public class Project {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "Project_SEQ", allocationSize = 100, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "Project_id")
    private Long projectId;

    @Column(name = "Name", unique = true, nullable = false)
    private String projectName;

    @Temporal(TemporalType.DATE)
    @Column(name = "Start_date", nullable = false)
    private Date projectStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "Finish_date", nullable = false)
    private Date projectFinishDate;

    @ManyToOne()
    @JoinColumn(name="Project_manager", nullable = true)
    private Employee projectManager;

    @ManyToOne()
    private Customer customer;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Sprint> sprints;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Task> tasks;

    public Project(String projectName, Date projectStartDate, Date projectFinishDate, Customer customer) {
        this.projectName = projectName;
        this.projectStartDate = projectStartDate;
        this.projectFinishDate = projectFinishDate;
        this.customer = customer;
    }

    public Project(String projectName, Date projectStartDate, Date projectFinishDate, Customer customer, Employee projectManager) {
        this(projectName, projectStartDate, projectFinishDate, customer);
        this.projectManager = projectManager;
    }

    public Project() {
    }

    public void setProjectManager(Employee projectManager) {
        this.projectManager = projectManager;
    }

    public void setSprints(List<Sprint> sprints) {
        this.sprints = sprints;
    }

    public Long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public Date getProjectFinishDate() {
        return projectFinishDate;
    }

    public Employee getProjectManager() {
        return projectManager;
    }

    public List<Sprint> getSprints() {
        return sprints;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public void setProjectFinishDate(Date projectFinishDate) {
        this.projectFinishDate = projectFinishDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
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

    public void addSprint(Sprint sprint) {
        if(sprints == null){
            sprints = new ArrayList<Sprint>();
        }
        sprints.add(sprint);
    }
}
