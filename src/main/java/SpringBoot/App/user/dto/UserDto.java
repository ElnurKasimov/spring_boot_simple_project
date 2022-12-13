package SpringBoot.App.user.dto;
import SpringBoot.App.role.Role;
import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private Set<Role> roles;

    public UserDto() {}

    public UserDto(String lastName, String firstName,  String email,  String password,  Set<Role> roles) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
