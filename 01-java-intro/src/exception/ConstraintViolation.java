package exception;

import java.util.StringJoiner;

public class ConstraintViolation {
    private String entityType;
    private String field;
    private String invalidValue;
    private String errorMessage;

    public ConstraintViolation(String entityType, String field, String invalidValue, String errorMessage) {
        this.entityType = entityType;
        this.field = field;
        this.invalidValue = invalidValue;
        this.errorMessage = errorMessage;
    }

    public String getEntityType() {
        return entityType;
    }

    public String getField() {
        return field;
    }

    public String getInvalidValue() {
        return invalidValue;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConstraintViolation)) return false;

        ConstraintViolation that = (ConstraintViolation) o;

        if (getEntityType() != null ? !getEntityType().equals(that.getEntityType()) : that.getEntityType() != null)
            return false;
        if (getField() != null ? !getField().equals(that.getField()) : that.getField() != null) return false;
        if (getInvalidValue() != null ? !getInvalidValue().equals(that.getInvalidValue()) : that.getInvalidValue() != null)
            return false;
        return getErrorMessage() != null ? getErrorMessage().equals(that.getErrorMessage()) : that.getErrorMessage() == null;
    }

    @Override
    public int hashCode() {
        int result = getEntityType() != null ? getEntityType().hashCode() : 0;
        result = 31 * result + (getField() != null ? getField().hashCode() : 0);
        result = 31 * result + (getInvalidValue() != null ? getInvalidValue().hashCode() : 0);
        result = 31 * result + (getErrorMessage() != null ? getErrorMessage().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ConstraintViolation.class.getSimpleName() + "[", "]")
                .add("entityType='" + entityType + "'")
                .add("field='" + field + "'")
                .add("invalidValue='" + invalidValue + "'")
                .add("errorMessage='" + errorMessage + "'")
                .toString();
    }
}
