package pl.javastart.equipy.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User with this pesel already exist!")
public class UserAlreadyExistException extends RuntimeException{
}
