package code.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 29.01.2017.
 */
public class ProjectStatisticReport {
    private String projectName;
    private Date projectStartDate;
    private Date projectFinishDate;
    private Employee projectManager;
    private Customer customer;

    private int sumEstimate;
    private int sumActualEstimate;

    private List<SprintStatisticReport> sprintStatisticReports;

    public ProjectStatisticReport(String projectName, Date projectStartDate, Date projectFinishDate, Employee projectManager, Customer customer) {
        this.projectName = projectName;
        this.projectStartDate = projectStartDate;
        this.projectFinishDate = projectFinishDate;
        this.projectManager = projectManager;
        this.customer = customer;
        sprintStatisticReports = new ArrayList<SprintStatisticReport>();
    }

    public void addSprintStatisticReport(SprintStatisticReport sprintStatisticReport){
        if(sprintStatisticReports == null){
            sprintStatisticReports = new ArrayList<SprintStatisticReport>();
        }
        sprintStatisticReports.add(sprintStatisticReport);
        sumEstimate += sprintStatisticReport.getSumEstimate();
        sumActualEstimate += sprintStatisticReport.getSumActualEstimate();
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

    public Customer getCustomer() {
        return customer;
    }

    public List<SprintStatisticReport> getSprintStatisticReports() {
        return sprintStatisticReports;
    }

    public int getSumEstimate() {
        return sumEstimate;
    }

    public int getSumActualEstimate() {
        return sumActualEstimate;
    }

    public int getDelta() {
        return sumEstimate - sumActualEstimate;
    }
}
