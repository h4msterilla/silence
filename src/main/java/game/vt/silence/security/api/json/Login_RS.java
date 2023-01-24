package game.vt.silence.security.api.json;

import lombok.Getter;

@Getter
public class Login_RS {

    public Login_RS(String type, String message) {
        status.type = type;
        status.message = message;
    }

    Status status = new Status();

    @Getter
    class Status {
        String type;
        String message;
    }

}
