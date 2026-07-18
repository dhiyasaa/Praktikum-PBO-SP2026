package util;

import error.ValidationException;
import model.User;

public class ValidationUtil {
    public static void validate(User user) throws ValidationException, NullPointerException {
        if (user.getEmail() == null) {
            throw new NullPointerException("Username is null");
        } else if (user.getEmail().isBlank()) {
            throw new ValidationException("Username is blank");
        } else if (user.getPassword() == null) {
            throw new NullPointerException("Password is null");
        } else if (user.getPassword().isBlank()) {
            throw new ValidationException("Password is blank");
        }
    }
}