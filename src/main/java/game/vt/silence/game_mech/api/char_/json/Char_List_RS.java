package game.vt.silence.game_mech.api.char_.json;

import lombok.Getter;

import java.util.List;

@Getter
public class Char_List_RS {

    Status status = new Status();

    public Char_List_RS(String type, String message, List<String> charnames){
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
