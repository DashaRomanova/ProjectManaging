package code.domain;

import javax.persistence.*;

/**
 * Created by Napha on 10.01.2017.
 */
@MappedSuperclass
public class User {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "User_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "User_id")
    private Long userId;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Surname", nullable = false)
    private String surname;
    @Column(name = "Login", unique = true, nullable = false)
    private String login;
    @Column(name = "Password", nullable = false)
    private String password;
    @Column(name = "Role", nullable = false)
    private String role;

    public User() {
    }

    public User(String name, String surname, String login, String password, Role role) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role.name();
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return Role.valueOf(role);
    }

    public void setRole(Role role) {
        this.role = role.name();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
