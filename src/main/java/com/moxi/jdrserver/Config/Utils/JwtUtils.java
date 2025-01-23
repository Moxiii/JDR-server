package com.moxi.jdrserver.Config.Utils;

import com.moxi.jdrserver.Models.User;
import com.moxi.jdrserver.Repository.UserRepository;
import com.moxi.jdrserver.Service.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
    private final long TOKEN_EXPIRATION_TIME = 60*60*1000*24;
    private final UserRepository userRepository;
    private final JwtParser jwtParser;
    public JwtUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.jwtParser = Jwts.parser().setSigningKey(SECRET_KEY).build();
    }

    @Bean
    public JwtDecoder JwtDecoder() { return NimbusJwtDecoder.withSecretKey(SECRET_KEY).build();
    }
public String createBypassToken(){
    User user = userRepository.findUserByUsername("SummyFrog");
    if (user == null) {
        throw new RuntimeException("Utilisateur non trouvé");
    }
    return generateToken(user);
}
    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername()).build();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + TOKEN_EXPIRATION_TIME);
        String token = Jwts.builder().setClaims(claims).setExpiration(expiration).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        return token;
    }
    public String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if(bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(TOKEN_PREFIX.length());
        }return null;
    }

    public String extractUsername(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getPayload();
            return true;
        }catch (JwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
