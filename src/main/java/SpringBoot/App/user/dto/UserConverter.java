package SpringBoot.App.user.dto;

import SpringBoot.App.user.User;

public class UserConverter {

    public static UserDto from(User user) {
        UserDto result = new UserDto();
        result.setId(user.getId());
        result.setLastName(user.getLastName());
        result.setFirstName(user.getFirstName());
        result.setEmail(user.getEmail());
        result.setPassword(user.getPassword());
        result.setRoles(user.getRoles());
        return result;
    }

    public static User to(UserDto userDto) {
        User result = new User();
        result.setId(userDto.getId());
        result.setLastName(userDto.getLastName());
        result.setFirstName(userDto.getFirstName());
        result.setEmail(userDto.getEmail());
        result.setPassword(userDto.getPassword());
        result.setRoles(userDto.getRoles());
        return result;
    }

}
