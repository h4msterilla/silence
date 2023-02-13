package game.vt.silence.exceptions;

import lombok.Getter;

@Getter
public class VTCharacterValueNotFoundException extends RuntimeException {
    public VTCharacterValueNotFoundException() {
        super("Value with such name is not found!");
    }
}
