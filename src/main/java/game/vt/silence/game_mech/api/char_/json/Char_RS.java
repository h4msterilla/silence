package game.vt.silence.game_mech.api.char_.json;

import lombok.Getter;

@Getter
public class Char_RS {

    Char_RS.Status status = new Char_RS.Status();

    public Char_RS(String type, String message) {
        status.type = type;
        status.message = message;
    }

    @Getter
    class Status {
        String type;
        String message;
    }
}
