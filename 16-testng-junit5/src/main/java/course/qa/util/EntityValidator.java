package course.qa.util;

import course.qa.exception.InvalidEntityDataException;

public interface EntityValidator<E> {
    void validate(E entity) throws InvalidEntityDataException;
}
