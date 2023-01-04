package game.vt.silence.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SecurityServiceImpl implements SecurityService{

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public String findLoggedInUsername() {
        return null;
    }

    @Override
    public void autoLogin(String username, String password) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
        authenticationManager.authenticate(token);
        if(token.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(token);
            System.out.printf("Auto login %s successfully!\n", username);
        }


    }
}
