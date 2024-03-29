package game.vt.silence.security.jwt;

import game.vt.silence.security.service.SecurityService;
import game.vt.silence.security.service.VTUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    VTUserService userService;
    @Autowired
    SecurityService securityService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //System.out.println(request.getRequestURI());
        if(!request.getRequestURI().startsWith("/api/")){
            filterChain.doFilter(request,response);
            return;
        }

        String jwt = parseJwt(request);
        logger.info("get token: {}", jwt);

        if (jwt == null | !jwtUtil.validateJwtToken(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtUtil.getUsernameFromJwtToken(jwt);

        if (!userService.existsByUsername(username)) {
            filterChain.doFilter(request, response);
            return;
        }

        securityService.nonPassAutoLogin(username, response);
        filterChain.doFilter(request, response);
    }


    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer "))
            return headerAuth.substring(7, headerAuth.length());

        return null;
    }

}
