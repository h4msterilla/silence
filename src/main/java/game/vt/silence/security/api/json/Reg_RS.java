package game.vt.silence.security.api.json;

import lombok.Getter;

@Getter
public class Reg_RS {

    public Reg_RS(String type, String message) {
        status.type = type;
        status.message = message;
    }

    Reg_RS.Status status = new Reg_RS.Status();

    @Getter
    class Status {
        String type;
        String message;
    }
}
