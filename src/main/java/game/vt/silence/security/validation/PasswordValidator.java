package game.vt.silence.security.validation;

import game.vt.silence.exceptions.VTValidationException;

public class PasswordValidator {

    public static void validate(String password) {

        if (password.length() < 8) throw new VTValidationException("password contains less then 8 symbols");
        if (password.length() > 50) throw new VTValidationException("password contains more then 50 symbols");

    }

}
