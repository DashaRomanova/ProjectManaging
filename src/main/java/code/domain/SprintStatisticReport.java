package code.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 29.01.2017.
 */
public class SprintStatisticReport {
    private String sprintName;
    private Date sprintStartDate;
    private Date sprintFinishDate;
    private int sumEstimate;
    private int sumActualEstimate;
    List<TaskStatisticReport> taskStatisticReports;

    public SprintStatisticReport(String sprintName,Date sprintStartDate,Date sprintFinishDate) {
        this.sprintName = sprintName;
        this.sprintStartDate = sprintStartDate;
        this.sprintFinishDate = sprintFinishDate;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public Date getSprintStartDate() {
        return sprintStartDate;
    }

    public void setSprintStartDate(Date sprintStartDate) {
        this.sprintStartDate = sprintStartDate;
    }

    public Date getSprintFinishDate() {
        return sprintFinishDate;
    }

    public void setSprintFinishDate(Date sprintFinishDate) {
        this.sprintFinishDate = sprintFinishDate;
    }

    public Integer getSumEstimate() {
        return sumEstimate;
    }

    public Integer getSumActualEstimate() {
        return sumActualEstimate;
    }

    public Integer getDelta() {
        return sumEstimate - sumActualEstimate;
    }

    public List<TaskStatisticReport> getTaskStatisticReports() {
        return taskStatisticReports;
    }

    public void setTaskStatisticReports(List<TaskStatisticReport> taskStatisticReports) {
        this.taskStatisticReports = taskStatisticReports;
    }

    public void addTaskStatisticReport(TaskStatisticReport taskStatisticReport) {
        if(taskStatisticReports == null){
            taskStatisticReports = new ArrayList<TaskStatisticReport>();
        }

        taskStatisticReports.add(taskStatisticReport);
    }

    public void setSumEstimate(int sumEstimate) {
        this.sumEstimate = sumEstimate;
    }

    public void setSumActualEstimate(int sumActualEstimate) {
        this.sumActualEstimate = sumActualEstimate;
    }
}
