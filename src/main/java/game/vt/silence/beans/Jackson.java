package game.vt.silence.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Jackson {

    @Bean
    ObjectMapper getJackson(){
        return new ObjectMapper();
    }

}
