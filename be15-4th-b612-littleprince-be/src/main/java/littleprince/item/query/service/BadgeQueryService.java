package littleprince.item.query.service;

import littleprince.item.query.dto.response.BadgeDto;
import littleprince.item.query.dto.response.MyBadgeResponse;
import littleprince.item.query.mapper.GetBadgeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeQueryService {

    private final GetBadgeMapper getBadgeMapper;

    public MyBadgeResponse getObtainedBadges(Long memberId) {
        // 1. get_badge 기준 badge 목록 조회
        List<BadgeDto> obtainedBadges = getBadgeMapper.findBadgesByMemberId(memberId);

        // 2. 변환
        List<BadgeDto> badgeDtos = obtainedBadges.stream()
                .map(b -> BadgeDto.builder()
                        .badgeId((long) b.getBadgeId())
                        .badgeName(b.getBadgeName())
                        .badgeMean(b.getBadgeMean())
                        .level(b.getLevel())
                        .build())
                .collect(Collectors.toList());

        // 3. 응답
        return MyBadgeResponse.builder()
                .badges(badgeDtos)
                .build();
    }
}
