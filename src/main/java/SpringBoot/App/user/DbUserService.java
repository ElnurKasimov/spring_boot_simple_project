package SpringBoot.App.user;

import SpringBoot.App.manufacture.Manufacture;
import SpringBoot.App.manufacture.dto.ManufactureConverter;
import SpringBoot.App.role.Role;
import SpringBoot.App.role.RoleService;
import SpringBoot.App.user.dto.UserConverter;
import SpringBoot.App.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@RequiredArgsConstructor
@Service
public class DbUserService implements UserService{
    private final UserRepository repository;
    private final RoleService roleService;

    @Override
    public Set<UserDto> listAll() {
        return repository.findAll()
                .stream()
                .map(UserConverter::from)
                .collect(Collectors.toSet());
    }

    @Override
    public UserDto getById(UUID id) {
        return UserConverter.from(repository.findById(id).orElse(null));
    }

    @Override
    public UserDto getByName(String lastName, String firstName) {
        return UserConverter.from(repository.getByName(lastName,firstName));
    }

    @Override
    public User save(UserDto userDto) {
        if (userDto.getId() == null) {
            userDto.setId(UUID.randomUUID());
        }
        return repository.save(UserConverter.to(userDto));
    }

    @Override
    public UserDto deleteById(UUID id) {
        UserDto toDelete = getById(id);
        if(toDelete != null) { repository.deleteById(id);}
        return toDelete;
    }

//    public void saveRoleRelations(User user) {
//        Set<Role> roles = user.getRoles();
//        for( Role role : roles) {
//            String roleName = role.getName();
//            UUID roleId = roleService.getIdByName(roleName);
//            repository.saveRoleRelations(roleId, user.getId());
//        }
//
////        user.getRoles().stream()
////        .map(Role::getName)
////        .forEach(roleName -> {
////            System.out.println("roleService.getIdByName(roleName) = " + roleService.getIdByName(roleName));
////            repository.saveRoleRelations(roleService.getIdByName(roleName), user.getId());
////        });
//    }
}
