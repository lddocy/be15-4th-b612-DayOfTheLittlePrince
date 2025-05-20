package littleprince.item.command.application.service;

import jakarta.transaction.Transactional;
import littleprince.item.query.mapper.GetBadgeCommandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BadgeCommandServiceImpl implements BadgeCommandService {

    private final GetBadgeCommandMapper getBadgeCommandMapper;

    @Override
    @Transactional
    public void selectBadge(Long memberId, Long badgeId) {

        // 1. 보유 여부 확인
        boolean hasBadge = getBadgeCommandMapper.hasBadge(memberId, badgeId);
        if (!hasBadge) {
            throw new IllegalArgumentException("해당 칭호를 보유하고 있지 않습니다.");
        }

        // 2. 전체 선택 해제
        getBadgeCommandMapper.clearSelectedBadge(memberId);

        // 3. 선택 설정
        getBadgeCommandMapper.updateSelectedBadge(memberId, badgeId);

        // 4. 선택된 칭호가 하나도 없으면 → LV0 자동 선택
        boolean anySelected = getBadgeCommandMapper.existsSelectedBadge(memberId);
        if (!anySelected) {
            getBadgeCommandMapper.selectLv0Badge(memberId);
        }
    }

}
