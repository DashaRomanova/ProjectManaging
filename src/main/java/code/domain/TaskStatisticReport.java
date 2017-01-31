package code.domain;

import java.util.Date;

/**
 * Created by Napha on 29.01.2017.
 */
public class TaskStatisticReport {
    private String taskName;
    private Date startDate;
    private Date expectedCompletionDate;
    private Date actualCompletionDate;
    private Integer estimate;
    private Integer actualEstimate;
    private Integer delta;


    public TaskStatisticReport(String taskName, Date startDate, Date expectedCompletionDate, Date actualCompletionDate, Integer estimate, Integer actualEstimate) {
        this.taskName = taskName;
        this.startDate = startDate;
        this.expectedCompletionDate = expectedCompletionDate;
        this.actualCompletionDate = actualCompletionDate;
        this.estimate = estimate;
        this.actualEstimate = actualEstimate;
        if(actualEstimate != null){
            delta =  estimate - actualEstimate;
        }

    }

    public String getTaskName() {
        return taskName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getExpectedCompletionDate() {
        return expectedCompletionDate;
    }

    public Date getActualCompletionDate() {
        return actualCompletionDate;
    }

    public Integer getEstimate() {
        return estimate;
    }

    public Integer getActualEstimate() {
        return actualEstimate;
    }

    public Integer getDelta() {
        return delta;
    }
}
