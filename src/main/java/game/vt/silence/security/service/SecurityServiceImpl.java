package game.vt.silence.security.service;

import game.vt.silence.security.jwt.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class SecurityServiceImpl implements SecurityService {
    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public String findLoggedInUsername() {
        String s;
        try {
           s = SecurityContextHolder.getContext().getAuthentication().getName();
        }catch (NullPointerException e){
            return null;
        }
        if(s.equalsIgnoreCase("anonymousUser")) s = null;
        logger.info("findLoggedInUsername = {}", s);
        return s;
    }

    @Override
    public boolean autoLogin(String username, String password, HttpServletResponse response) {

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
            jwtUtil.setJwtHeader(response, userDetails);
            logger.info("Auto login {} successfully!", username);
            return true;
        }
        return false;
    }

    @Override
    public void nonPassAutoLogin(String username, HttpServletResponse response){
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        jwtUtil.setJwtHeader(response, userDetails);
        logger.info("Non pass auto login {} successfully!", username);
    }
}
