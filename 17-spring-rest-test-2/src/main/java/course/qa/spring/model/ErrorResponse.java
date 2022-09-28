package course.qa.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.engine.internal.Collections;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private List<ConstraintViolation> violations = List.of();

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
