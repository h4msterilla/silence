package game.vt.silence.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public String findLoggedInUsername() {
        String s;
        try {
           s = SecurityContextHolder.getContext().getAuthentication().getName();
        }catch (NullPointerException e){
            return null;
        }
        if(s.equalsIgnoreCase("anonymousUser")) s = null;
        return s;
    }

    @Override
    public boolean autoLogin(String username, String password) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        try {
            authenticationManager.authenticate(token);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
            System.out.printf("Auto login %s successfully!\n", username);
            return true;
        }
        return false;
    }

    @Override
    public void nonPassAutoLogin(String username){
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        System.out.printf("Non pass auto login %s successfully!\n", username);
    }
}
