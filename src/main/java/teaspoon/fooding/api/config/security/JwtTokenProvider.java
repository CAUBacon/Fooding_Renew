package teaspoon.fooding.api.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import teaspoon.fooding.service.CustomUserDetailsService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtTokenProvider {

    private final CustomUserDetailsService customUserDetailsService;
    @Value("spring.jwt.secret")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String issueToken(String userId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles", roles);
        LocalDateTime now = LocalDateTime.now();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(toDate(now))
                .setExpiration(toDate(now.plusDays(31)))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        try {
            Long id = Long.parseLong(getUserId(token));
            UserDetails userDetails = customUserDetailsService.findById(id);
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public String getUserId(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("x-auth-token");
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwtToken);

            return !claims.getBody().getExpiration().before(toDate(LocalDateTime.now()));
        } catch (Exception e) {
            log.info("jwt parse error", e);
            return false;
        }
    }

    private Date toDate(LocalDateTime now) {
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }
}
