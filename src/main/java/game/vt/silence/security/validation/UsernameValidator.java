package game.vt.silence.security.validation;


import javax.xml.bind.ValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator {

    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9._-]+$";

    public static void validate(String username) throws ValidationException {

        if(username.length() < 4) throw new ValidationException("contains less then 4 letters");
        if(username.length() > 20) throw new ValidationException("contains more then 20 letters");

        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(username);
        if(!matcher.matches()) throw new ValidationException("username contains wrong symbols");

    }

}
