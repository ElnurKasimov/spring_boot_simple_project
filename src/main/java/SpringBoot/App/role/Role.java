package SpringBoot.App.role;

import SpringBoot.App.user.User;
import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "role")
public class Role {
    private UUID id;
    private String name;
    private Set<User> users;

    public Role() {}

    public Role (String name) {
        this.name = name;
    }


    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.PERSIST)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
