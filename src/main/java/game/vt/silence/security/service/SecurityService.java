package game.vt.silence.security.service;

import javax.servlet.http.HttpServletResponse;

public interface SecurityService {

    String findLoggedInUsername();

    boolean autoLogin(String username, String password, HttpServletResponse response);

    void nonPassAutoLogin(String username, HttpServletResponse response);

}
