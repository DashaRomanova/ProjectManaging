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
    @Column(name = "Description")
    private String taskDescription;
    @Column(name = "Estimate")
    private int estimate;
    @Column(name = "Requested_estimate")
    private String requestedEstimate;
    @Temporal(TemporalType.DATE)
    @Column(name = "Expiration_date")
    private Date expirationDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "Requested_expiration_date")
    private Date requestedExpirationDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "Actual_completion_date")
    private Date actualCompletionDate;

    @Column(name="Status")
    private String status;

    @ManyToOne()
    @JoinColumn(name="Employee")
    private Employee employee;

    @ManyToOne()
    @JoinColumn(name="Qualification_id")
    private Qualification taskQualification;

    @ManyToOne()
    @JoinColumn(name = "Parent_influencing_task_id")
    private Task parentInfluencingTask;

    @OneToMany(mappedBy = "parentInfluencingTask")
    private List<Task> influencingTasks;

    @ManyToOne()
    @JoinColumn(name = "Parent_sub_task_id")
    private Task parentSubTask;

    @OneToMany(mappedBy = "parentSubTask")
    private List<Task> subTasks;

    @ManyToOne()
    private Sprint sprint;


    public Task() {
        influencingTasks = new ArrayList<Task>();
        subTasks = new ArrayList<Task>();
    }

    public Task(String taskName, String taskDescription, int estimate, Date expirationDate, String status, Qualification taskQualification) {
        this();
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.estimate = estimate;
        this.expirationDate = expirationDate;
        this.status = status;
        this.taskQualification = taskQualification;
    }

    public Task(String taskName, String taskDescription, int estimate, Date expirationDate, String status, Employee employee, Qualification taskQualification) {
        this(taskName, taskDescription, estimate, expirationDate, status, taskQualification);
        this.employee = employee;
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

    public String getRequestedEstimate() {
        return requestedEstimate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Date getActualCompletionDate() {
        return actualCompletionDate;
    }

    public Date getRequestedExpirationDate() {
        return requestedExpirationDate;
    }

    public String getStatus() {
        return status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Qualification getTaskQualification() {
        return taskQualification;
    }

    public List<Task> getInfluencingTasks() {
        return influencingTasks;
    }

    public List<Task> getSubTasks() {
        return subTasks;
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

    public void setRequestedEstimate(String requestedEstimate) {
        this.requestedEstimate = requestedEstimate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setRequestedExpirationDate(Date requestedExpirationDate) {
        this.requestedExpirationDate = requestedExpirationDate;
    }

    public void setActualCompletionDate(Date actualCompletionDate) {
        this.actualCompletionDate = actualCompletionDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setTaskQualification(Qualification taskQualification) {
        this.taskQualification = taskQualification;
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

    public Task getParentSubTask() {
        return parentSubTask;
    }

    public void setParentSubTask(Task parentSubTask) {
        this.parentSubTask = parentSubTask;
    }

    public void setSubTasks(List<Task> subTasks) {
        this.subTasks = subTasks;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }
}
