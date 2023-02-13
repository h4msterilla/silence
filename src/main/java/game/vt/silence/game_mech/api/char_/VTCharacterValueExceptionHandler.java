package game.vt.silence.game_mech.api.char_;

import game.vt.silence.exceptions.VTCharacterValueNotFoundException;
import game.vt.silence.game_mech.api.char_.DTO.CharEditRS;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class VTCharacterValueExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = VTCharacterValueNotFoundException.class)
    protected ResponseEntity<Object> handleVTCharacterValueNotFoundException(RuntimeException e, WebRequest webRequest){
        return handleExceptionInternal(
                e,
                new CharEditRS("",""),
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                webRequest
        );
    }

}
