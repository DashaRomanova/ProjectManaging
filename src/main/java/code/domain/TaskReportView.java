package code.domain;

/**
 * Created by Napha on 29.01.2017.
 */
public class TaskReportView {
    private String taskName;
    private String sprintName;
    private String projectName;

    public TaskReportView(String taskName, String projectName, String sprintName) {
        this.taskName = taskName;
        this.projectName = projectName;
        this.sprintName = sprintName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getSprintName() {
        return sprintName;
    }

    public String getProjectName() {
        return projectName;
    }
}
