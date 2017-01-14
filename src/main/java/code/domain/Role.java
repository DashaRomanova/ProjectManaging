package code.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Napha on 10.01.2017.
 */
@Entity
@Table(name = "Role")
public class Role {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "Role_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "Role_id")
    private Long roleId;
    @Column(name = "Role_name", unique = true, nullable = false)
    private String roleName;
    @OneToMany(mappedBy = "role")
    private List<Employee> employees;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setName(String name) {
        this.roleName = name;
    }
}
