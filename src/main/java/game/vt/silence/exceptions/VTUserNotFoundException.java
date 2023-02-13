package game.vt.silence.exceptions;

public class VTUserNotFoundException extends RuntimeException{
    public VTUserNotFoundException(){
        super("User with such name is not found!");
    }
}
