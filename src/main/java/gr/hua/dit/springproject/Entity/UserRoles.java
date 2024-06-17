package gr.hua.dit.springproject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

class UserRolesId implements Serializable {
    private User user;
    private Role role;

    public UserRolesId() {}

    public UserRolesId(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRolesId that = (UserRolesId) o;
        return user.getId().equals(that.user.getId()) && role.getRole_id().equals(that.role.getRole_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(user.getId(), role.getRole_id());
    }
}

@Entity
@IdClass(UserRolesId.class)
@Table(name="User_Roles", schema = "dtst")
public class UserRoles {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
        @JoinColumn(name="User_id")
        @JsonBackReference(value="UserRoles")
        private User user;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
        @JoinColumn(name="Role_id")
        @JsonBackReference(value="Roles")
        private Role role;

        public UserRoles() {}

        public UserRoles(User user, Role role) {
            this.user = user;
            this.role = role;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return "UserRoles{" + ",\n" +
                    "\tuser_id=" + user + ",\n" +
                    "\trole_id=" + role + ",\n" +
                    '}';
        }
}
