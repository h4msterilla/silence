package game.vt.silence.exceptions.handlers;

import game.vt.silence.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class VTExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            VTUserNameOccupiedException.class,
            VTUserNotFoundException.class,
            VTUserWrongPasswordException.class,

            VTCharacterNameOccupiedException.class,
            VTCharacterNotFoundException.class,

            VTCharacterValueNotFoundException.class
    })
    protected ResponseEntity<Object> handleVTUserExceptions(RuntimeException e, WebRequest webRequest){
        return handleExceptionInternal(
                e,
                new StatusRS(e),
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                webRequest
        );
    }

}
