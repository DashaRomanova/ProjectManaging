package code.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 11.01.2017.
 */

@Entity
@Table(name = "Project")
public class Project {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "Project_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "Project_id")
    private Long projectId;

    @Column(name = "Name")
    private String projectName;

    @Temporal(TemporalType.DATE)
    @Column(name = "Start_date")
    private Date projectStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "Finish_date")
    private Date projectFinishDate;

    @ManyToOne()
    @JoinColumn(name="Project_manager")
    private Employee projectManager;

    @ManyToOne()
    private Customer customer;

    @OneToMany(mappedBy = "project")
    private List<Sprint> sprints;

    public Project(String projectName, Date projectStartDate, Date projectFinishDate) {
        this.projectName = projectName;
        this.projectStartDate = projectStartDate;
        this.projectFinishDate = projectFinishDate;
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
}
