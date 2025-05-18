package littleprince.infra.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;


@Repository
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository{
    @Value("${jwt.refresh-expiration}")
    private long jwtRefreshExpiration;

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(Long memberId, String refreshToken) {
        Duration ttl = Duration.ofMillis(jwtRefreshExpiration);

        redisTemplate.opsForValue().set("refresh:" + memberId, refreshToken, ttl);
    }

    @Override
    public String find(Long memberId) {
        return (String) redisTemplate.opsForValue().get("refresh:" + memberId);
    }

    @Override
    public void delete(Long memberId) {
        redisTemplate.delete("refresh:" + memberId);
    }
}
