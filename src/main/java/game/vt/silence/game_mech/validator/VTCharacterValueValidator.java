package game.vt.silence.game_mech.validator;

import game.vt.silence.exceptions.VTCharacterValueNotFoundException;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.stereotype.Component;

@Component
public class VTCharacterValueValidator {

    public void validate(VTCharacter vtCharacter, VTCharacterValue vtCharacterValue, String upDown){
        if(false) throw new VTCharacterValueNotFoundException("some math text");
    }

}
