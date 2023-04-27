package game.vt.silence.beans;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class DefaultVTCharacterValuesLoader {
    String valuesStr;

    @SneakyThrows
    public DefaultVTCharacterValuesLoader() {
        File valuesFile = ResourceUtils.getFile("classpath:character-values");
        InputStream in = new FileInputStream(valuesFile);
        valuesStr = new String(in.readAllBytes());
    }

    /*public List<VTCharacterValue> getAsList() {
        ArrayList<VTCharacterValue> vtCharacterValues = new ArrayList<>();

        for (String line : valuesStr.split("\n")) {
            if (line.startsWith("#")) continue;
            String[] splitLine = line.split(",");
            VTCharacterValue vtCharacterValue = new VTCharacterValue();
            vtCharacterValue.setType(splitLine[0]);
            vtCharacterValue.setName(splitLine[1]);
            vtCharacterValue.setValue(0);
            vtCharacterValues.add(vtCharacterValue);
        }
        return vtCharacterValues;
    }*/
}
