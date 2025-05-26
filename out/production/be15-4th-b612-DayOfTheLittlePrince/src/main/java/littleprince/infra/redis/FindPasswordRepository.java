package littleprince.infra.redis;

public interface FindPasswordRepository {
    void save(String email, String uuid);
    String find(String uuid);
    void delete(String uuid);
}
