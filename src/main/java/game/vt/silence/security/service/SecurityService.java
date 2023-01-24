package game.vt.silence.security.service;

import game.vt.silence.security.model.VT_UserNotFoundException;
import game.vt.silence.security.model.VT_UserUsernameOccupiedException;
import game.vt.silence.security.model.VT_UserWrongPasswordException;

import javax.servlet.http.HttpServletResponse;

public interface SecurityService {

    String findLoggedInUsername();

    void regUser(String username, String password) throws VT_UserUsernameOccupiedException;

    void autoLogin(String username, String password, HttpServletResponse response) throws VT_UserNotFoundException, VT_UserWrongPasswordException;

    void nonPassAutoLogin(String username, HttpServletResponse response);

}
