package code.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Napha on 10.01.2017.
 */
@Entity
@Table(name = "SubTask")
public class SubTask extends BaseTask{
    @ManyToOne()
    @JoinColumn(name = "Task_parent_id")
    private Task parentTask;



    public SubTask() {
    }

    public SubTask(String taskName, int estimate, Date expirationDate, Status status, String taskDescription, Qualification taskQualification) {
        super(taskName, estimate, expirationDate, status, taskDescription, taskQualification);
    }

    public SubTask(String taskName, String taskDescription, int estimate, Date expirationDate, Status status, Employee employee, Qualification taskQualification) {
        super(taskName, taskDescription, estimate, expirationDate, status, employee, taskQualification);
    }
}
