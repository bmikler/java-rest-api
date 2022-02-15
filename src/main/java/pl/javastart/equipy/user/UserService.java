package pl.javastart.equipy.user;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.user.dto.UserDtoMapper;
import pl.javastart.equipy.user.dto.UserDto;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public UserService(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }

    public List<UserDto> findAll() {

        return userRepository.findAll()
                .stream()
                .map(userDtoMapper::map)
                .toList();
    }

    public List<UserDto> findByLastName(String lastName) {
        return userRepository.findByLastNameContainsIgnoreCase(lastName)
                .stream()
                .map(userDtoMapper::map)
                .toList();
    }

    public Optional<UserDto> findById(Long id){
        return userRepository.findById(id)
                .map(userDtoMapper::map);
    }

    public UserDto addUser(UserDto userInput) {

        User user = userDtoMapper.map(userInput);

        if (userRepository.findByPesel(user.getPesel()).isPresent()) {
            throw new UserAlreadyExistException();
        }

        User savedUser = userRepository.save(user);

        return userDtoMapper.map(savedUser);
    }

    public UserDto editUser(Long id, UserDto userInput) {

        userRepository.findByPesel(userInput.getPesel()).ifPresent(user -> {
                    if (user.getId() != id) {
                        throw new UserAlreadyExistException();
                    }
                }
        );

        User user = userDtoMapper.map(userInput);
        User userSaved = userRepository.save(user);

        return userDtoMapper.map(userSaved);

    }

}
