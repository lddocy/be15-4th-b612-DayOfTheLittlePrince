package littleprince.infra.redis;

public interface RefreshTokenRepository {
    void save(Long memberId, String refreshToken);
    String find(Long memberId);
    void delete(Long memberId);
}
