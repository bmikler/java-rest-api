package pl.javastart.equipy.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.javastart.equipy.assigment.Assignment;
import pl.javastart.equipy.assigment.dto.AssignmentDto;
import pl.javastart.equipy.assigment.dto.AssignmentDtoMapper;
import pl.javastart.equipy.user.dto.UserDto;
import pl.javastart.equipy.user.dto.UserDtoMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserDtoMapper userDtoMapper;
    private AssignmentDtoMapper assignmentDtoMapper;
    private UserService userService;

    @BeforeEach
    void init() {
        userRepository = mock(UserRepository.class);
        userDtoMapper = mock(UserDtoMapper.class);
        assignmentDtoMapper = mock(AssignmentDtoMapper.class);
        userService = new UserService(userRepository, userDtoMapper, assignmentDtoMapper);
    }

    @Test
    void findAll() {
    }

    @Test
    void findByLastName() {
    }

    @Test
    void findById() {
    }

    @Test
    void addUser() {
    }

    @Test
    void editUser() {
    }

    @Test
    void getAssignmentByUserUserNotFoundThrowException() {

        //given
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        //when/then
        assertThrows(
                UserNotFoundException.class,
                () -> userService.getAssignmentByUser(1L)
        );

    }

    @Test
    void getAssignmentByUserUserFoundReturnAssignmentsListWithOneAssignment() {

        //given
        Long userId = 1L;
        User user = new User(userId, "testName", "testLastName", "testPesel");
        Assignment assignment = new Assignment(1L, LocalDateTime.MIN, LocalDateTime.MAX, user, null);
        user.addAssignment(assignment);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        AssignmentDto assignmentDto = new AssignmentDto(1L, LocalDateTime.MIN, LocalDateTime.MAX, null, 1L, null, null);
        when(assignmentDtoMapper.map(assignment)).thenReturn(assignmentDto);

        List<AssignmentDto> expected = List.of(assignmentDto);
        List<AssignmentDto> actual = userService.getAssignmentByUser(userId);

        assertEquals(expected, actual);

    }

    @Test
    void getAssignmentByUserUserFoundReturnAssignmentsListWithListAssignment() {

        //given
        Long userId = 1L;
        User user = new User(userId, "testName", "testLastName", "testPesel");
        Assignment assignment = new Assignment(1L, LocalDateTime.MIN, LocalDateTime.MAX, user, null);
        Assignment assignment2 = new Assignment(2L, LocalDateTime.MIN, LocalDateTime.MAX, user, null);
        user.addAssignment(assignment);
        user.addAssignment(assignment2);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        AssignmentDto assignmentDto = new AssignmentDto(1L, LocalDateTime.MIN, LocalDateTime.MAX, null, 1L, null, null);
        AssignmentDto assignmentDto2 = new AssignmentDto(2L, LocalDateTime.MIN, LocalDateTime.MAX, null, 1L, null, null);
        when(assignmentDtoMapper.map(assignment)).thenReturn(assignmentDto);
        when(assignmentDtoMapper.map(assignment2)).thenReturn(assignmentDto2);

        List<AssignmentDto> expected = List.of(assignmentDto, assignmentDto2);
        List<AssignmentDto> actual = userService.getAssignmentByUser(userId);

        assertEquals(expected, actual);

    }

    @Test
    void getAssignmentByUserUserFoundReturnEmptyList() {

        //given
        Long userId = 1L;
        User user = new User(userId, "testName", "testLastName", "testPesel");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        List<AssignmentDto> expected = Collections.emptyList();
        List<AssignmentDto> actual = userService.getAssignmentByUser(userId);

        assertEquals(expected, actual);

    }

}