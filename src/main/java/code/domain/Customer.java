package code.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Napha on 10.01.2017.
 */
@Entity
@Table(name = "Customer")
public class Customer extends User {
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Project> projects;

    @ManyToOne()
    private Company company;


    public Customer(String name, String surname, String login, String password, Role role) {
        super(name, surname, login, password, role);
        projects = new ArrayList<Project>();
    }

    public Customer() {
    }

    public void addProject(Project project){
        if(projects == null){
            projects = new ArrayList<Project>();
        }
        projects.add(project);
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Project> getProjects() {
        return projects;
    }
}
