package SpringBoot.App.role;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Primary
@RequiredArgsConstructor
@Service
public class DbRoleService implements RoleService{
    private  final RoleRepository repository;

    @Override
    public Set<Role> listAll() {

        return new HashSet<>(repository.findAll());
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public Role deleteById(UUID id) {
        return null;
    }

    @Override
    public Role getByName(String name) {
        return null;
    }

    @Override
    public Role getById(UUID id) {
        return null;
    }

    @Override
    public Set<Role> getRolesFromNames(String[] rolesNames) {
        return null;
    }
}
