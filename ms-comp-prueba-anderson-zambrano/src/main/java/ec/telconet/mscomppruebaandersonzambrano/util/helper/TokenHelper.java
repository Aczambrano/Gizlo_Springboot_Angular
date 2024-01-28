package ec.telconet.mscomppruebaandersonzambrano.util.helper;

import ec.telconet.mscomppruebaandersonzambrano.main.entity.response.UsuarioResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class TokenHelper {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;



    // retrieve username from jwt token
    public String getUsernameFromToken(String token, String key) throws Exception {
        return getClaimFromToken(token, key, Claims::getSubject);
    }


    // retrieve expiration date from jwt token
    private Date getExpirationDateFromToken(String token, String key) throws Exception {
        return getClaimFromToken(token, key, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, String key, Function<Claims, T> claimsResolver) throws Exception {
        final Claims claims = getAllClaimsFromToken(token, key);
        return claimsResolver.apply(claims);
    }

    // for retrieveing any information from token we will need the secret key
    public Claims getAllClaimsFromToken(String token, String key) throws Exception {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    // while creating the token -
    // 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
    // 2. Sign the JWT using the HS512 algorithm and secret key.
    // 3. According to JWS Compact
    // Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    // compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String user, String key) {
        return Jwts.builder().setClaims(new HashMap<>(claims)).setSubject(user)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 36000))
                .signWith(SignatureAlgorithm.HS512, key).compact();
    }

    // validate token
    public Boolean validateToken(String token, String user, String key) throws Exception {
        final String username = getUsernameFromToken(token,key);
        return (username.equals(user));
    }


    // check if the token has expired
    public Boolean isTokenExpired(String token, String key) throws Exception {
        final Date expiration = getExpirationDateFromToken(token, key);
        return expiration.before(new Date());
    }

    // generate token for user
    public String generateToken(String user, UsuarioResponse claimsDetails, String key) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("info", claimsDetails);
        return doGenerateToken(claims, user, key);
    }
}
