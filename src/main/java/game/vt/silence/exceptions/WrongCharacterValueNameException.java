package game.vt.silence.exceptions;

import lombok.Getter;

@Getter
public class WrongCharacterValueNameException extends RuntimeException {
    String wrongName;

    public WrongCharacterValueNameException(String wrongName) {
        if (wrongName == null)
            this.wrongName = "plz write value name";
        else
            this.wrongName = wrongName;
    }
}
