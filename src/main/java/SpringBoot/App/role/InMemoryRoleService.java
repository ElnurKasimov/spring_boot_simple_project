package SpringBoot.App.role;

import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class InMemoryRoleService implements RoleService{
    private Map<UUID, Role> roles = new HashMap<>();

    @Override
    public Set<Role> listAll() {
        return new HashSet<>(roles.values());
    }

    @Override
    public Role save(Role role) {
        if (role.getId() == null) {
            role.setId(UUID.randomUUID());
        }
        roles.put(role.getId(), role);
        return role;
    }

    @Override
    public Role getById(UUID id) {return roles.get(id);}

    @Override
    public Role getByName(String name) {
        return roles.values()
                .stream()
                .filter(role -> role.getName().equals(name))
                .findFirst().orElse(null);
    }

    @Override
    public Role deleteById(UUID id) {return roles.remove(id);}

    @Override
    public Set<Role> getRolesFromNames(String[] rolesNames) {
        Set<Role> roles = new HashSet<>();
        Arrays.stream(rolesNames).forEach(role -> roles.add(getByName(role)));
        return roles;
    }

}
