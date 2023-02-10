package game.vt.silence.security.service;

import game.vt.silence.security.model.VTUser;
import game.vt.silence.exceptions.VTUserNotFoundException;
import game.vt.silence.exceptions.VTUserNameOccupiedException;
import game.vt.silence.exceptions.VTUserWrongPasswordException;

import javax.servlet.http.HttpServletResponse;

public interface SecurityService {

    String findLoggedInUsername();

    VTUser findLoggedInVT_User() throws VTUserNotFoundException;

    void regUser(String username, String password) throws VTUserNameOccupiedException;

    void autoLogin(String username, String password, HttpServletResponse response) throws VTUserNotFoundException, VTUserWrongPasswordException;

    void nonPassAutoLogin(String username, HttpServletResponse response);

}
