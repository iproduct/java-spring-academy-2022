package course.spring.intro.web;

import course.spring.intro.dto.ErrorResponse;
import course.spring.intro.exception.InvalidEntityDataException;
import course.spring.intro.exception.NonexistingEntityException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNonexistingEntityException(NonexistingEntityException ex) {
        return ResponseEntity.status(NOT_FOUND).body(new ErrorResponse(NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidEntityDataException(InvalidEntityDataException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(BAD_REQUEST.value(), ex.getMessage(), ex.getConstraintViolations()));
    }

}
