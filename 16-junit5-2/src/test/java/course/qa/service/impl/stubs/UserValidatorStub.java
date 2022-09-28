package course.qa.service.impl.stubs;

import course.qa.exception.InvalidEntityDataException;
import course.qa.model.User;
import course.qa.util.EntityValidator;

public class UserValidatorStub  implements EntityValidator<User> {
    @Override
    public void validate(User entity) throws InvalidEntityDataException {
    }
}
