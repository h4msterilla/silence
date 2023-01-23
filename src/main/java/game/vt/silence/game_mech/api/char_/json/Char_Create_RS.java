package game.vt.silence.game_mech.api.char_.json;

import lombok.Getter;

@Getter
public class Char_Create_RS {

    Status status = new Status();

    public Char_Create_RS(String type, String message) {
        status.type = type;
        status.message = message;
    }

    @Getter
    class Status {
        String type;
        String message;
    }
}

