package SpringBoot.App.role;

import SpringBoot.App.manufacture.Manufacture;
import SpringBoot.App.manufacture.dto.ManufactureConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
        if (role.getId() == null) {
            role.setId(UUID.randomUUID());
        }
        return repository.save(role);
    }

    @Override
    public Role deleteById(UUID id) {
        Role toDelete = getById(id);
        if(toDelete != null) { repository.deleteById(id);}
        return toDelete;
    }

    @Override
    public Role getByName(String name) {
        return repository.getByName(name);
    }

    @Override
    public Role getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Set<Role> getRolesFromNames(String[] rolesNames) {
        return Arrays.stream(rolesNames)
                .map(this::getByName)
                .collect(Collectors.toSet());
    }
}
