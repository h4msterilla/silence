package game.vt.silence.exceptions;

public class VTUserWrongPasswordException extends RuntimeException{
    public VTUserWrongPasswordException(){
        super("This password is wrong!");
    }
}
