package code.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Napha on 10.01.2017.
 */

@Entity
@Table(name = "Employee")
public class Employee extends User{
    @ManyToOne()
    @JoinColumn(name="Role_id")
    private Role role;
    @ManyToOne()
    @JoinColumn(name="Qualification_id")
    private Qualification qualification;

    @OneToMany(mappedBy = "projectManager")
    private List<Project> projects;


    @OneToMany(mappedBy = "employee")
    private List<Task> tasks;

    @ManyToOne()
    private Company company;


    public Employee(String name, String surname, String login, String password, Qualification qualification, Role role) {
        super(name, surname, login, password);
        this.qualification = qualification;
        this.role = role;
    }

    public Employee() {
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Role getRole() {
        return role;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
