package code.domain;

import javax.persistence.*;

/**
 * Created by Napha on 10.01.2017.
 */
@MappedSuperclass
public class User {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "User_SEQ", allocationSize = 100, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "User_id")
    private Long userId;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Surname", nullable = false)
    private String surname;
    @Column(name = "Username", unique = true, nullable = false)
    private String username;
    @Column(name = "Password", nullable = false)
    private String password;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @Column(name = "Role", nullable = false)
    private String role;

    public User() {
    }

    public User(String name, String surname, String login, String password, Role role) {
        this.name = name;
        this.surname = surname;
        this.username = login;
        this.password = password;
        this.role = role.name();
        this.enabled = true;

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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
