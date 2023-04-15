package game.vt.silence.game_mech.model.factories;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.VTCharacterValue;
import game.vt.silence.game_mech.service.operarius.VTCharacterService;
import game.vt.silence.game_mech.service.operarius.VTCharacterValueService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class VTCharacterFactoryImpl {

    @Autowired
    VTCharacterService vtCharacterService;
    @Autowired
    VTCharacterValueService vtCharacterValueService;

    File file;
    String valuesFile;

    @SneakyThrows
    public VTCharacterFactoryImpl() {
        file = ResourceUtils.getFile("classpath:character-values");
        InputStream in = new FileInputStream(file);
        valuesFile = new String(in.readAllBytes());
    }

    /*public void createVTCharacter(String charname) {
        VTCharacter vtCharacter = new VTCharacter();
        vtCharacter.setCharname(charname);

        for (String line : valuesFile.split("\n")) {
            if (line.startsWith("#")) continue;

            VTCharacterValue vtCharacterValue = new VTCharacterValue();
            String[] splitLine = line.split(",");

            vtCharacterValue.setType(splitLine[0]);
            vtCharacterValue.setName(splitLine[1]);
            vtCharacterValue.setValue(0);

            vtCharacterValue.setVtCharacter(vtCharacter);
            vtCharacter.addVTCharacterValue(vtCharacterValue);

            //vtCharacterValueService.save(vtCharacterValue);
        }

        vtCharacterService.saveVTCharacter(vtCharacter);
    }*/

}
