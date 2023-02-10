package game.vt.silence.exceptions;

import lombok.Getter;

@Getter
public class VTCharacterValueWrongNameException extends RuntimeException {
    String wrongName;

    public VTCharacterValueWrongNameException(String wrongName) {
        if (wrongName == null)
            this.wrongName = "plz write value name";
        else
            this.wrongName = wrongName;
    }
}
