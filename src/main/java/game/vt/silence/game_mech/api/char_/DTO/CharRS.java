package game.vt.silence.game_mech.api.char_.DTO;

import lombok.Getter;

@Getter
public class CharRS {

    CharRS.Status status = new CharRS.Status();

    public CharRS(String type, String message) {
        status.type = type;
        status.message = message;
    }

    @Getter
    class Status {
        String type;
        String message;
    }
}
