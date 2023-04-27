package game.vt.silence.vaadin.silencemenu;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class IncognitoNamesGenerator {

    private static String firstNames = "Nameless, Unknown, Mystery, Anonymous, Secret, Faceless, Ghostly, Silent, Stealthy, Pandoric, Silent";
    private static List<String> firstNamesList;
    private static String secondNames = "Incognito, Agent, Hero, Legend, Sentinel, Enigma, Whisper, Hunter, Stranger, Saboteur, Specter, Nomad, Guardian";
    private static List<String> secondNamesList;

    public IncognitoNamesGenerator(){
        firstNamesList = Arrays.stream(firstNames.split(", ")).toList();
        firstNames = null;

        secondNamesList = Arrays.stream(secondNames.split(", ")).toList();
        secondNames = null;
    }

    public static String getName(){
        return getRnd(firstNamesList) + " " + getRnd(secondNamesList);
    }

    private static String getRnd(List<String> names){

        return names.get((int) (Math.random()*(names.size()-1)));

        //return null;
    }

}
