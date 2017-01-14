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
public class Task extends BaseTask{
    @ManyToOne()
    @JoinColumn(name = "Task_parent_id")
    private Task parentTask;

    @OneToMany(mappedBy = "parentTask")
    private List<Task> influencingTasks;

    @OneToMany(mappedBy = "parentTask")
    private List<SubTask> subTasks;

    @ManyToOne()
    private Sprint sprint;



    public Task() {
        influencingTasks = new ArrayList<Task>();
        subTasks = new ArrayList<SubTask>();
    }

    public Task(String taskName, int estimate, Date expirationDate, Status status, String taskDescription, Qualification taskQualification) {
        super(taskName, estimate, expirationDate, status, taskDescription, taskQualification);
        influencingTasks = new ArrayList<Task>();
        subTasks = new ArrayList<SubTask>();
    }

    public Task(String taskName, String taskDescription, int estimate, Date expirationDate, Status status, Employee employee, Qualification taskQualification) {
        super(taskName, taskDescription, estimate, expirationDate, status, employee, taskQualification);
        influencingTasks = new ArrayList<Task>();
        subTasks = new ArrayList<SubTask>();
    }


    public void setInfluencingTasks(List<Task> influencingTasks) {
        this.influencingTasks = influencingTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public List<Task> getInfluencingTasks() {
        return influencingTasks;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }
}
