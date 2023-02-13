package game.vt.silence.exceptions;

public class VTCharacterNameOccupiedException extends RuntimeException{
    public VTCharacterNameOccupiedException(){
        super("Character name is already occupied!");
    }
}
