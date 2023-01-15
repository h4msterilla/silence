package game.vt.silence.security.jwt;

import io.jsonwebtoken.*;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Getter
    private String secret = "somesecretkeyvaluelololo";
    //@Autowired
    //SecurityService securityService;


    public String generateToken(String username) {
        Date now = new Date();
        Date exp = Date.from(LocalDateTime.now().plusMinutes(30).atZone(ZoneId.systemDefault()).toInstant());;
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            logger.info("validate JWT success");
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    /*public void processJwtHeader(HttpServletResponse response){
        if(securityService.findLoggedInUsername() == null){
                clearJwtHeader(response);
            return;
        }
        setJwtHeader(response);
    }*/

    public void setJwtHeader(HttpServletResponse response, UserDetails userDetails){
        String jwt = generateToken(userDetails.getUsername());
        response.setHeader("Authorization", "Bearer " + jwt);
        logger.info("Set JWT: {}",jwt);
    }

    public void clearJwtHeader(HttpServletResponse response){
        if(response.containsHeader("Authorization"))
            response.setHeader("Authorization","");
    }

}
