package course.qa.exception;

import java.util.List;
import java.util.StringJoiner;

public class InvalidEntityDataException extends Exception {
    private List<ConstraintViolation> violations;

    public InvalidEntityDataException() {
    }

    public InvalidEntityDataException(String message) {
        super(message);
    }

    public InvalidEntityDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEntityDataException(Throwable cause) {
        super(cause);
    }

    public InvalidEntityDataException(List<ConstraintViolation> violations) {
        this.violations = violations;
    }

    public InvalidEntityDataException(String message, List<ConstraintViolation> violations) {
        super(message);
        this.violations = violations;
    }

    public InvalidEntityDataException(String message, Throwable cause, List<ConstraintViolation> violations) {
        super(message, cause);
        this.violations = violations;
    }

    public InvalidEntityDataException(Throwable cause, List<ConstraintViolation> violations) {
        super(cause);
        this.violations = violations;
    }

    public List<ConstraintViolation> getViolations() {
        return violations;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ")
                .add(super.toString())
                .add("violations=" + violations)
                .toString();
    }
}
