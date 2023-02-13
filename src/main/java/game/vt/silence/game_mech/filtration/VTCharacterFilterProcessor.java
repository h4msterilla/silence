package game.vt.silence.game_mech.filtration;

import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class VTCharacterFilterProcessor {

    @Autowired
    List<VTCharacterValueFilter> filters;

    public void doFilter(VTCharacterValue vtCharacterValue, String upDown){
        filters
                .stream()
                .sorted(Comparator.comparingInt(VTCharacterValueFilter::getOrder))
                .forEach(x -> x.doFilter(vtCharacterValue, upDown));
    }

}
