package game.vt.silence.exceptions;

public class VTUserNameOccupiedException extends RuntimeException{
    public VTUserNameOccupiedException(){
        super("Username is already occupied!");
    }
}
