package game.vt.silence.game_mech.model;

import lombok.Getter;

@Getter
public class WrongCharacterValueNameException extends Exception {
    String wrongName;

    public WrongCharacterValueNameException(String wrongName) {
        if (wrongName == null)
            this.wrongName = "plz write value name";
        else
            this.wrongName = wrongName;
    }
}
