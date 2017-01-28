package code.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 10.01.2017.
 */
@Entity
@Table(name = "Task")
public class Task{
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "Task_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "Task_id")
    private Long taskId;
    @Column(name = "Name", unique = true, nullable = false)
    private String taskName;
    @Column(name = "Description", nullable = false)
    private String taskDescription;
    @Column(name = "Estimate", nullable = false)
    private Integer estimate;
    @Column(name = "Requested_estimate")
    private Integer requestedEstimate;
    @Temporal(TemporalType.DATE)
    @Column(name = "Start_date")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "Expected_completion_date")
    private Date expectedCompletionDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "Actual_completion_date")
    private Date actualCompletionDate;

    @Column(name="Status", nullable = false)
    private String status;

    @ManyToOne()
    @JoinColumn(name="Employee", nullable = true)
    private Employee employee;

    @Column(name = "Qualification", nullable = false)
    private String taskQualification;

    @ManyToOne()
    @JoinColumn(name = "Parent_influencing_task_id")
    private Task parentInfluencingTask;

    @OneToMany(mappedBy = "parentInfluencingTask", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Task> influencingTasks;

    @ManyToOne()
    private Sprint sprint;

    @ManyToOne()
    private Project project;


    public Task() {
    }

    public Task(String taskName, String taskDescription, int estimate, Status status, Qualification taskQualification, Project project) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.estimate = estimate;
        this.status = status.name();
        this.taskQualification = taskQualification.name();
        this.project = project;

        influencingTasks = new ArrayList<Task>();
    }

    public Long getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public int getEstimate() {
        return estimate;
    }


    public Date getActualCompletionDate() {
        return actualCompletionDate;
    }

    public Status getStatus() {
        return Status.valueOf(status);
    }

    public Employee getEmployee() {
        return employee;
    }

    public Qualification getTaskQualification() {
        return Qualification.valueOf(taskQualification);
    }

    public List<Task> getInfluencingTasks() {
        return influencingTasks;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }


    public void setActualCompletionDate(Date actualCompletionDate) {
        this.actualCompletionDate = actualCompletionDate;
    }

    public void setStatus(Status status) {
        this.status = status.name();
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setTaskQualification(Qualification taskQualification) {
        this.taskQualification = taskQualification.name();
    }

    public Task getParentInfluencingTask() {
        return parentInfluencingTask;
    }

    public void setParentInfluencingTask(Task parentInfluencingTask) {
        this.parentInfluencingTask = parentInfluencingTask;
    }

    public void setInfluencingTasks(List<Task> influencingTasks) {
        this.influencingTasks = influencingTasks;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void addInfluencingTask(Task influencingTask){
        if(this.influencingTasks == null){
            this.influencingTasks = new ArrayList<Task>();
        }
        this.influencingTasks.add(influencingTask);

    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpectedCompletionDate() {
        return expectedCompletionDate;
    }

    public void setExpectedCompletionDate(Date expectedCompletionDate) {
        this.expectedCompletionDate = expectedCompletionDate;
    }

    public Integer getRequestedEstimate() {
        return requestedEstimate;
    }

    public void setRequestedEstimate(Integer requestedEstimate) {
        this.requestedEstimate = requestedEstimate;
    }
}
