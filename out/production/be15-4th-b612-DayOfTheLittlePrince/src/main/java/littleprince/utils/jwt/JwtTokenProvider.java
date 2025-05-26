package littleprince.utils.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import littleprince.common.exception.BusinessException;
import littleprince.common.exception.GlobalErrorCode;
import littleprince.common.exception.UnAuthorizationException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long jwtRefreshExpiration;

    @Getter
    private SecretKey secretKey;

    @PostConstruct
    public void init(){
        byte[] key = Decoders.BASE64.decode(jwtSecret);
        secretKey = Keys.hmacShaKeyFor(key);
    }

    public String createAccessToken(Long memberId, String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+ jwtExpiration);
        return Jwts.builder()
                .subject(email)
                .claim("memberId", memberId)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public String createRefreshToken(Long memberId, String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+ jwtRefreshExpiration);
        return Jwts.builder()
                .subject(email)
                .claim("memberId", memberId)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            throw new UnAuthorizationException(GlobalErrorCode.UNAUTHORIZED_REQUEST);
            /* 글로벌 단위에서 캐치 하는 중*/
        } catch (ExpiredJwtException e) {
            throw new BusinessException(GlobalErrorCode.INVALID_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new BadCredentialsException("지원하지 않는 요청입니다.", e);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("잘못된 요청입니다.", e);
        }
    }

    public String getEmailFromJWT(String token) {
        return Jwts.parser().verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Long getMemberIdFromJWT(String token) {
        return Jwts.parser().verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("memberId", Long.class);
    }

    public Date getExpirationDate(String token) {
        return Jwts.parser().verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }
}
