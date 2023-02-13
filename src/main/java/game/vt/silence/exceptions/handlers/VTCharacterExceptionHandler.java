package game.vt.silence.exceptions.handlers;

import game.vt.silence.exceptions.VTCharacterNameOccupiedException;
import game.vt.silence.exceptions.VTCharacterNotFoundException;
import game.vt.silence.exceptions.VTCharacterValueNotFoundException;
import game.vt.silence.game_mech.api.char_.DTO.CharRS;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class VTCharacterExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = VTCharacterNameOccupiedException.class)
    protected ResponseEntity<Object> handleVTCharacterNameOccupiedException(RuntimeException e, WebRequest webRequest) {
        return handleExceptionInternal(
                e,
                new CharRS("charnamealreadyused", "some text"),
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                webRequest);
    }

    @ExceptionHandler(value = VTCharacterNotFoundException.class)
    protected ResponseEntity<Object> handleVTCharacterNotFoundException(RuntimeException e, WebRequest webRequest) {
        return handleExceptionInternal(
                e,
                new CharRS("wrong charname", "character not found"),
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                webRequest);
    }

}
