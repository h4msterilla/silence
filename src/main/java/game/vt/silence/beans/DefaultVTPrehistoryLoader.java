package game.vt.silence.beans;

import game.vt.silence.game_mech.model.VTPrehistoryEpisode;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultVTPrehistoryLoader {

    private String prehistoryData;

    @SneakyThrows
    public DefaultVTPrehistoryLoader() {
        File dataFile = ResourceUtils.getFile("classpath:prehistories");
        InputStream in = new FileInputStream(dataFile);
        prehistoryData = new String(in.readAllBytes());
    }

    public List<VTPrehistoryEpisode> getDefaultVTPrehistoryEpisodeList() {
        ArrayList<VTPrehistoryEpisode> vtPrehistoryEpisodes = new ArrayList<>();

        for (String line : prehistoryData.split("\n")) {

            String[] splitLine = line.split(";");

            VTPrehistoryEpisode vtPrehistoryEpisode = new VTPrehistoryEpisode();

            vtPrehistoryEpisode.setFirstDice(Integer.parseInt(splitLine[0]));
            vtPrehistoryEpisode.setFirstDiceDescription(splitLine[1]);
            vtPrehistoryEpisode.setSecondDice(Integer.parseInt(splitLine[2]));
            vtPrehistoryEpisode.setSecondDiceDescription(splitLine[3]);
            vtPrehistoryEpisode.setEpisodeDescription(splitLine[4]);
            vtPrehistoryEpisode.setBonusesDescription(splitLine[5]);

           /* vtPrehistoryEpisodes.add(vtPrehistoryEpisode);
            System.out.println(vtPrehistoryEpisode.getFirstDice() + " " + vtPrehistoryEpisode.getFirstDiceDescription()
                    + " " + vtPrehistoryEpisode.getSecondDice() + " " + vtPrehistoryEpisode.getSecondDiceDescription() +
                    " " + vtPrehistoryEpisode.getEpisodeDescription() + " " + vtPrehistoryEpisode.getBonusesDescription());
        */
        }

        return vtPrehistoryEpisodes;
    }

}
