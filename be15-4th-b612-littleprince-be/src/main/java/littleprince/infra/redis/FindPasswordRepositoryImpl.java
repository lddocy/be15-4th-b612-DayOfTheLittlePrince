package littleprince.infra.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FindPasswordRepositoryImpl implements FindPasswordRepository {
    private final String EMAIL_PREFIX = "find:pass:";
    private final Duration TTL = Duration.ofMinutes(10);

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(String email, String uuid) {
        redisTemplate.opsForValue().set(EMAIL_PREFIX + uuid, email, TTL);
    }

    @Override
    public String find(String uuid) {
        return (String) redisTemplate.opsForValue().get(EMAIL_PREFIX + uuid);
    }

    @Override
    public void delete(String uuid) {
        redisTemplate.delete(EMAIL_PREFIX + uuid);
    }
}
