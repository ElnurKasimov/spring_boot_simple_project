package SpringBoot.App.user;


import SpringBoot.App.user.dto.UserConverter;
import SpringBoot.App.user.dto.UserDto;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InMemoryUserService implements UserService {
    @Getter
    private Map<UUID, User> users = new HashMap<>();


    @Override
    public Set<UserDto> listAll() {
        return users.values().stream().map(UserConverter::from).collect(Collectors.toSet());
    }

    @Override
    public UserDto getById(UUID id) {return UserConverter.from(users.get(id));
    }

    @Override
    public UserDto getByName(String lastName, String firstName) {
        return users.values()
                .stream()
                .map(UserConverter::from)
                .filter(userDto -> (userDto.getLastName().equals(lastName) && userDto.getFirstName().equals(firstName) ) )
                .findFirst().orElse(null);
    }

    @Override
    public User save(UserDto userDto) {
        if (userDto.getId() == null) {
            userDto.setId(UUID.randomUUID());
        }
        users.put(userDto.getId(), UserConverter.to(userDto));
        return UserConverter.to(userDto);
    }

    @Override
    public UserDto deleteById(UUID id) {
        return UserConverter.from(users.remove(id));
    }
}
