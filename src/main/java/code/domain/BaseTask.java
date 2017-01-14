package code.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Napha on 10.01.2017.
 */
@MappedSuperclass
public abstract class BaseTask {
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

    @ManyToOne()
    @JoinColumn(name="Status_id")
    private Status status;

    @ManyToOne()
    @JoinColumn(name="Employee")
    private Employee employee;

    @ManyToOne()
    @JoinColumn(name="Qualification_id")
    private Qualification taskQualification;

    public BaseTask() {
    }

    public BaseTask(String taskName, int estimate, Date expirationDate, Status status, String taskDescription, Qualification taskQualification) {
        this.taskName = taskName;
        this.estimate = estimate;
        this.expirationDate = expirationDate;
        this.status = status;
        this.taskDescription = taskDescription;
        this.taskQualification = taskQualification;
    }

    public BaseTask(String taskName, String taskDescription, int estimate, Date expirationDate, Status status, Employee employee, Qualification taskQualification) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.estimate = estimate;
        this.expirationDate = expirationDate;
        this.status = status;
        this.employee = employee;
        this.taskQualification = taskQualification;
    }

    public void setRequestedEstimate(String requestedEstimate) {
        this.requestedEstimate = requestedEstimate;
    }

    public void setRequestedExpirationDate(Date requestedExpirationDate) {
        this.requestedExpirationDate = requestedExpirationDate;
    }

    public void setActualCompletionDate(Date actualCompletionDate) {
        this.actualCompletionDate = actualCompletionDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setTaskQualification(Qualification taskQualification) {
        this.taskQualification = taskQualification;
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

    public Date getRequestedExpirationDate() {
        return requestedExpirationDate;
    }

    public Date getActualCompletionDate() {
        return actualCompletionDate;
    }

    public Status getStatus() {
        return status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Qualification getTaskQualification() {
        return taskQualification;
    }
}
