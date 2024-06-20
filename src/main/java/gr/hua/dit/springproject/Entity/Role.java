package gr.hua.dit.springproject.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Role_id")
    private Long role_id;

    @Enumerated(EnumType.STRING)
    @Column(name="Name")
    private EnumRole name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonManagedReference(value="Roles")
    private List<UserRoles> userRoles;

    public Role() {}

    public Role(Long role_id, EnumRole name) {
        this.role_id = role_id;
        this.name = name;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public EnumRole getName() {
        return name;
    }

    public void setName(EnumRole name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" + ",\n" +
                "\trole_id=" + role_id + ",\n" +
                "\tname=" + name + "',\n" +
                '}';
    }
}
