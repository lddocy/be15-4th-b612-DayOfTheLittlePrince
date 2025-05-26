package littleprince.item.command.application.service;

public interface BadgeCommandService {
    void selectBadge(Long memberId, Long badgeId);

    void addBadge(Long memberId, int level);

    void addDefaultBadge(Long memberId);
}
