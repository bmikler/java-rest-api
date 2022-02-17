package pl.javastart.equipy.user.dto;

import org.springframework.stereotype.Component;
import pl.javastart.equipy.user.User;

@Component
public class UserDtoMapper {

    public UserDto map(User user) {

        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPesel()
        );

    }

    public User map(UserDto user) {

        return new User(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPesel(),
                null
        );

    }

}
