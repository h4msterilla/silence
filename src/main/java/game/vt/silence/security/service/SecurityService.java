package game.vt.silence.security.service;

public interface SecurityService {

    String findLoggedInUsername();

    boolean autoLogin(String username, String password);

}
