package digital.and.bookstoreapi.util;

import digital.and.bookstoreapi.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public final class JwtUtil {
    private JwtUtil() {};

    private static final long EXPIRY_MINUTES = 60L;
    private static final SecretKey KEY = Jwts.SIG.HS256.key().build();

    public static String createToken(String subject) {
        final Instant now = Instant.now();

        return Jwts.builder()
                .subject(subject)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(EXPIRY_MINUTES, ChronoUnit.MINUTES)))
                .signWith(KEY)
                .compact();
    }

    public static String extractSubject(String token) {
        return getClaims(token).getSubject();
    }

    public static boolean validateToken(String token, User user) {
        return user.getUsername().equals(extractSubject(token)) && !isTokenExpired(token);
    }

    private static boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private static Claims getClaims(String token) {
        JwtParser parser = Jwts.parser()
                .decryptWith(KEY)
                .build();

        return parser.parseSignedClaims(token)
                .getPayload();
    }
}
