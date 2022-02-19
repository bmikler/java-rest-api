package pl.javastart.equipy.user.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.javastart.equipy.user.User;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoMapperTest {

    private UserDtoMapper userDtoMapper;

    @BeforeEach
    void init() {
        userDtoMapper = new UserDtoMapper();
    }

    @Test
    void mapToDtoNullValueIllegalArgumentException() {

        User user = null;

        assertThrows(
                IllegalArgumentException.class,
                () -> userDtoMapper.map(user)
        );

    }

    @Test
    void mapToDtoCorrectValues() {

        User user = new User(1L, "testName", "testLastName", "testPesel");
        UserDto userDto = new UserDto(1L, "testName", "testLastName", "testPesel");

        UserDto expected = userDto;
        UserDto actual = userDtoMapper.map(user);

        assertEquals(expected, actual);

    }

    @Test
    void mapNullValueIllegalArgumentException() {

        UserDto user = null;

        assertThrows(
                IllegalArgumentException.class,
                () -> userDtoMapper.map(user)
        );

    }

    @Test
    void mapCorrectValues() {

        User user = new User(1L, "testName", "testLastName", "testPesel");
        UserDto userDto = new UserDto(1L, "testName", "testLastName", "testPesel");

        User expected = user;
        User actual = userDtoMapper.map(userDto);

        assertEquals(expected, actual);

    }

}