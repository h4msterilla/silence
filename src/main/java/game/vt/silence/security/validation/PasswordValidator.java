package game.vt.silence.security.validation;

import javax.xml.bind.ValidationException;

public class PasswordValidator {

    public static void validate(String password) throws ValidationException {

        if(password.length() < 8) throw new ValidationException("contains less then 8 symbols");
        if(password.length() > 50) throw new ValidationException("contains more then 50 symbols");

    }

}
