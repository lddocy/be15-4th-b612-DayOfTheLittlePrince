package littleprince.item.command.application.service;

import littleprince.member.command.domain.aggregate.MemberDTO;
import littleprince.member.exception.MemberErrorCode;
import littleprince.member.query.mapper.MemberQueryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import littleprince.common.exception.BusinessException;
import littleprince.item.Exception.ItemErrorCode;
import littleprince.item.query.mapper.GetBadgeCommandMapper;
import littleprince.member.query.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BadgeCommandServiceImpl implements BadgeCommandService {

    private final GetBadgeCommandMapper getBadgeCommandMapper;
    private final MemberQueryMapper memberQueryMapper;
    private final MemberQueryService memberQueryService;

    @Override
    @Transactional
    public void selectBadge(Long memberId, Long badgeId) {

        // 1. 보유 여부 확인
        boolean hasBadge = getBadgeCommandMapper.hasBadge(memberId, badgeId);
        if (!hasBadge) {
            throw new BusinessException(ItemErrorCode.ITEM_BADGE_NOT_OWNED);
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

    @Override
    @Async
    @Transactional
    public void addBadge(Long memberId, int level) {
        /* 1. 유저 레벨 확인하기! */
//        MemberInfoResponse member = memberQueryService.getMemberInfo(memberId);
        MemberDTO member = memberQueryMapper.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.USER_NOT_FOUND));
        log.info("뱃지 지급을 위한 유저 찾음!");

        /* 2. DB에 벳지 추가해주기! */
        getBadgeCommandMapper.insertGetBadge(member.getMemberId(), level);
    }

    @Async
    @Transactional
    public void addDefaultBadge(Long memberId) {
        /* 1. 유저 레벨 확인하기! */
//        MemberInfoResponse member = memberQueryService.getMemberInfo(memberId);
        MemberDTO member = memberQueryMapper.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.USER_NOT_FOUND));
        log.info("뱃지 지급을 위한 유저 찾음!");

        /* 2. DB에 벳지 추가해주기! */
        getBadgeCommandMapper.insertDefaultGetBadge(memberId, 0);
    }

}
