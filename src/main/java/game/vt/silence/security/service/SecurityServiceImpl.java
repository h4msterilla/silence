package game.vt.silence.security.service;

import game.vt.silence.security.jwt.JwtUtil;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.exceptions.VTUserNotFoundException;
import game.vt.silence.exceptions.VTUserNameOccupiedException;
import game.vt.silence.exceptions.VTUserWrongPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    VTUserService userService;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public String findLoggedInUsername() {
        String s;
        try {
            s = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (NullPointerException e) {
            return null;
        }
        if (s.equalsIgnoreCase("anonymousUser")) s = null;
        logger.info("findLoggedInUsername = {}", s);
        return s;
    }

    @Override
    public VTUser findLoggedInVT_User() throws VTUserNotFoundException {
        String username = findLoggedInUsername();
        if(username == null) return null;

        VTUser user = userService.findByUsername(username);

        if(username == null) throw new VTUserNotFoundException();
        return user;
    }

    @Override
    public void regUser(String username, String password) throws VTUserNameOccupiedException {

        if (userService.existsByUsername(username)) throw new VTUserNameOccupiedException();

        VTUser vt_user = new VTUser();
        vt_user.setUsername(username);
        vt_user.setPassword(password);
        vt_user.setEncodedPassword(encoder.encode(password));

        userService.save(vt_user);
        logger.info("reg new user: {}", username);
    }


    @Override
    public void autoLogin(String username, String password, HttpServletResponse response) throws VTUserNotFoundException, VTUserWrongPasswordException {

        if (!userService.existsByUsername(username)) throw new VTUserNotFoundException();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        try {
            authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            throw new VTUserWrongPasswordException();
        }

        if (!token.isAuthenticated()) throw new VTUserWrongPasswordException();

        SecurityContextHolder.getContext().setAuthentication(token);
        jwtUtil.setJwtHeader(response, userDetails);
        logger.info("Auto login {} successfully!", username);

    }

    @Override
    public void nonPassAutoLogin(String username, HttpServletResponse response) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        jwtUtil.setJwtHeader(response, userDetails);
        logger.info("Non pass auto login {} successfully!", username);
    }
}
