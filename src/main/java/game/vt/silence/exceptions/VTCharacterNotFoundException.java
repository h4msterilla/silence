package game.vt.silence.exceptions;
public class VTCharacterNotFoundException extends RuntimeException{
    public VTCharacterNotFoundException(){
        super("Character with such name is not found!");
    }
}
