package exception;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class ConstraintViolationException extends Exception {
    private List<ConstraintViolation> violations = List.of();
    public ConstraintViolationException() {
    }

    public ConstraintViolationException(String message) {
        super(message);
    }

    public ConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConstraintViolationException(Throwable cause) {
        super(cause);
    }

    public ConstraintViolationException(List<ConstraintViolation> violations) {
        this.violations = violations;
    }

    public ConstraintViolationException(String message, List<ConstraintViolation> violations) {
        super(message);
        this.violations = violations;
    }

    public ConstraintViolationException(String message, Throwable cause, List<ConstraintViolation> violations) {
        super(message, cause);
        this.violations = violations;
    }

    public ConstraintViolationException(Throwable cause, List<ConstraintViolation> violations) {
        super(cause);
        this.violations = violations;
    }

    public List<ConstraintViolation> getViolations() {
        return violations;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ConstraintViolationException.class.getSimpleName() + "[", "]")
                .add("violations=" + violations)
                .add("message='" + getMessage() + "'")
                .add("stackTrace=" + Arrays.toString(getStackTrace()))
                .toString();
    }
}
