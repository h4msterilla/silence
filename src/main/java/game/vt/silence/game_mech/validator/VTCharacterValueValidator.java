package game.vt.silence.game_mech.validator;

import game.vt.silence.exceptions.WrongCharacterValueNameException;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.stereotype.Component;

@Component
public class VTCharacterValueValidator {

    public void validate(VTCharacter vtCharacter, VTCharacterValue vtCharacterValue, String upDown){
        if(false) throw new WrongCharacterValueNameException("some math text");
    }

}
