package pl.javastart.equipy.assigment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Assignment already have end date")
public class AssignmentAlreadyReturnedException extends RuntimeException{
}
