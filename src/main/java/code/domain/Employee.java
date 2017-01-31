package code.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Napha on 10.01.2017.
 */

@Entity
@Table(name = "Employee")
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "EmployeeMapping",
                classes = @ConstructorResult(
                        targetClass = ViewEmployees.class,
                        columns = {
                                @ColumnResult(name = "USER_ID", type = Long.class),
                                @ColumnResult(name = "SURNAME"),
                                @ColumnResult(name = "NAME"),
                                @ColumnResult(name = "overtime", type = Integer.class)})),
        @SqlResultSetMapping(
                name = "EmployeeMapping2",
                classes = @ConstructorResult(
                        targetClass = ViewEmployees.class,
                        columns = {
                                @ColumnResult(name = "USER_ID", type = Long.class),
                                @ColumnResult(name = "SURNAME"),
                                @ColumnResult(name = "NAME")}))
})

public class Employee extends User{
    @Column(name = "Qualification", nullable = false)
    private String qualification;

    @OneToMany(mappedBy = "projectManager", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Project> projects;


    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Task> tasks;

    public Employee(String name, String surname, String login, String password, Qualification qualification, Role role) {
        super(name, surname, login, password, role);
        this.qualification = qualification.name();
    }

    public Employee() {
        projects = new ArrayList<Project>();
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        if(tasks == null){
            tasks = new ArrayList<Task>();
        }
        tasks.add(task);
    }

    public Qualification getQualification() {
        return Qualification.valueOf(qualification);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification.name();
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        if(projects == null){
            projects = new ArrayList<Project>();
        }
        projects.add(project);
    }

    public void removeProject(Project project) {
        if(projects != null){
            projects.remove(project);
        }
    }
}
