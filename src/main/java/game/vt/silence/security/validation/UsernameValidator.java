package game.vt.silence.security.validation;


import game.vt.silence.exceptions.VTValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator {

    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9._-]+$";

    public static void validate(String username) {

        if (username.length() < 4) throw new VTValidationException("username contains less then 4 letters");
        if (username.length() > 20) throw new VTValidationException("username contains more then 20 letters");

        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches()) throw new VTValidationException("username contains wrong symbols");

    }

}
