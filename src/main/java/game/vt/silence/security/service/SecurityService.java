package game.vt.silence.security.service;

import game.vt.silence.security.model.VTUser;

import javax.servlet.http.HttpServletResponse;

public interface SecurityService {

    String findLoggedInUsername();

    VTUser findLoggedInVT_User();

    void regUser(String username, String password);

    void autoLogin(String username, String password, HttpServletResponse response);

    void nonPassAutoLogin(String username, HttpServletResponse response);

}
