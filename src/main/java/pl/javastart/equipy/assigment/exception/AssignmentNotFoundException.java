package pl.javastart.equipy.assigment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Assignment not found")
public class AssignmentNotFoundException extends RuntimeException{
}
