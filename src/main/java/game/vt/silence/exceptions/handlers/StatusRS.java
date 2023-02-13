package game.vt.silence.exceptions.handlers;

import lombok.Getter;
import lombok.Setter;

@Getter
public class StatusRS {

    private Status status;

    public StatusRS(StatusType statusType, String message){
        status = new Status();
        status.setType(statusType.toString().toLowerCase());
        status.setMessage(message);
    }

    public StatusRS(RuntimeException e){
        status = new Status();
        status.setType(
                e.getClass().getSimpleName().replace("Exception","").toLowerCase());
        status.setMessage(e.getMessage());
    }

    @Getter
    @Setter
    private class Status{
        private String type;
        private String message;
    }
}
