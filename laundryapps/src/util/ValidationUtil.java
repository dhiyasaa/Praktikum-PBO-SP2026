package util;

import error.ValidationException;
import model.User;

public class ValidationUtil {

    public static void validate(User user) throws ValidationException {

        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new ValidationException("Username tidak boleh kosong");
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new ValidationException("Password tidak boleh kosong");
        }

    }

}