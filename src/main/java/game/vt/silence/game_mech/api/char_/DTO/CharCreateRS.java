package game.vt.silence.game_mech.api.char_.DTO;

import lombok.Getter;

@Getter
public class CharCreateRS {

    Status status = new Status();

    public CharCreateRS(String type, String message) {
        status.type = type;
        status.message = message;
    }

    @Getter
    class Status {
        String type;
        String message;
    }
}

