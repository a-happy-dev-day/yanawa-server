package fashionable.simba.yanawaserver.global.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class JwtTokenProvider {
    private static final String ROLES = "roles";
    private final String secretKey;
    private final String refreshKey;
    private final long validityRefreshTokenMilliseconds;
    private final long validityAccessTokenMilliseconds;

    public JwtTokenProvider(String secretKey, String refreshKey, long validityRefreshTokenMilliseconds, long validityAccessTokenMilliseconds) {
        this.secretKey = secretKey;
        this.refreshKey = refreshKey;
        this.validityRefreshTokenMilliseconds = validityRefreshTokenMilliseconds;
        this.validityAccessTokenMilliseconds = validityAccessTokenMilliseconds;
    }

    public String createAuthorizationToken(String principal, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(principal);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityAccessTokenMilliseconds);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .claim(ROLES, roles)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }


    public String createAuthenticationToken(String principal) {
        Claims claims = Jwts.claims().setSubject(principal);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityAccessTokenMilliseconds);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .claim(ROLES, Collections.emptyList())
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    public String createRefreshToken(String principal) {
        Claims claims = Jwts.claims().setSubject(principal);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityRefreshTokenMilliseconds);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .claim(ROLES, Collections.emptyList())
            .signWith(SignatureAlgorithm.HS256, refreshKey)
            .compact();
    }

    public String getPrincipal(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public List<String> getRoles(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(ROLES, List.class);
    }

    public String getPrincipalByRefreshToken(String refreshToken) {
        return Jwts.parser().setSigningKey(refreshKey).parseClaimsJws(refreshToken).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException exception) {
            return false;
        }
    }

    public boolean validateRefreshToken(String refreshToken) {
        try {
            Jwts.parser().setSigningKey(refreshKey).parseClaimsJws(refreshToken);
            return true;
        } catch (JwtException | IllegalArgumentException exception) {
            return false;
        }
    }
}
