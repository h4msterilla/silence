package game.vt.silence.exceptions;

import lombok.Getter;

@Getter
public class VTCharacterValueNotFoundException extends RuntimeException {
    String wrongName;

    public VTCharacterValueNotFoundException(String wrongName) {
        if (wrongName == null)
            this.wrongName = "plz write value name";
        else
            this.wrongName = wrongName;
    }
}
