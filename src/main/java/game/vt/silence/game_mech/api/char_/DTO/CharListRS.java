package game.vt.silence.game_mech.api.char_.DTO;

import lombok.Getter;

import java.util.List;

@Getter
public class CharListRS {

    Status status = new Status();

    public CharListRS(String type, String message, List<String> charnames){
        status.type = type;
        status.message = message;
        this.charnames = charnames;
    }

    @Getter
    class Status{
        String type;
        String message;
    }

    List<String> charnames;

}
