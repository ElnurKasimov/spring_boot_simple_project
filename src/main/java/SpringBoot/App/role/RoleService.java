package SpringBoot.App.role;

import java.util.Set;
import java.util.UUID;

public interface RoleService {

    public Set<Role> listAll();

    public Role save(Role role);

    public Role deleteById(UUID id);

    public Role getByName(String name);

    public Role getById(UUID id);

    public Set<Role> getRolesFromNames(String[] rolesNames);
}
