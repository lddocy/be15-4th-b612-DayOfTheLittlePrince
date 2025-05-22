package littleprince.item.query.service;

import littleprince.common.exception.BusinessException;
import littleprince.item.query.dto.BadgeDTO;
import littleprince.item.query.dto.response.*;
import littleprince.item.query.dto.response.MyBadgeResponse;
import littleprince.item.query.mapper.GetBadgeQueryMapper;
import littleprince.member.exception.MemberErrorCode;
import littleprince.member.query.mapper.MemberQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeQueryService {

    private final GetBadgeQueryMapper getBadgeQueryMapper;
    private final MemberQueryMapper memberQueryMapper;
    public MyBadgeResponse getObtainedBadges(Long memberId) {
        // 1. get_badge 기준 badge 목록 조회
        List<BadgeDTO> obtainedBadges = getBadgeQueryMapper.findBadgesByMemberId(memberId);

        // 2. 변환
        List<BadgeDTO> badgeDtos = obtainedBadges.stream()
                .map(b -> BadgeDTO.builder()
                        .badgeId(b.getBadgeId())
                        .badgeName(b.getBadgeName())
                        .badgeMean(b.getBadgeMean())
                        .level(b.getLevel())
                        .isSelected(b.getIsSelected())
                        .build())
                .collect(Collectors.toList());

        // 3. 응답
        return MyBadgeResponse.builder()
                .badges(badgeDtos)
                .build();
    }

    public String getSelectedBadge(Long memberId) {
        memberQueryMapper.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.USER_NOT_FOUND));
        return getBadgeQueryMapper.findSelectedBadgeByMemberId(memberId);
    }

}
