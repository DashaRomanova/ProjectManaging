package code.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Napha on 10.01.2017.
 */

@Entity
@Table(name = "Employee")
public class Employee extends User{
    @ManyToOne()
    @JoinColumn(name="Qualification_id")
    private Qualification qualification;

    @OneToMany(mappedBy = "projectManager")
    private List<Project> projects;


    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<Task> tasks;

    @ManyToOne()
    private Company company;


    public Employee(String name, String surname, String login, String password, Qualification qualification, String role) {
        super(name, surname, login, password, role);
        this.qualification = qualification;
    }

    public Employee() {
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
        return qualification;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
