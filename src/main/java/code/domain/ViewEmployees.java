package code.domain;

/**
 * Created by Napha on 29.01.2017.
 */
public class ViewEmployees {
    private Long id;
    private String surname;
    private String name;
    private Integer overtime;

    public ViewEmployees(Long id, String surname, String name, Integer overtime) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.overtime = overtime;
    }

    public ViewEmployees(Long id, String surname, String name) {
        this.id = id;
        this.surname = surname;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public Integer getOvertime() {
        return overtime;
    }
}
