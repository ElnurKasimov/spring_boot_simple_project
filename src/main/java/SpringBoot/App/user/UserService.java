package SpringBoot.App.user;

import SpringBoot.App.user.dto.UserDto;

import java.util.Set;
import java.util.UUID;

public interface UserService {

    public Set<UserDto> listAll ();

    public UserDto getById(UUID id);

    public UserDto getByName(String lastName, String firstName);

    public User save(UserDto userDto);

    public UserDto deleteById(UUID id);

}
