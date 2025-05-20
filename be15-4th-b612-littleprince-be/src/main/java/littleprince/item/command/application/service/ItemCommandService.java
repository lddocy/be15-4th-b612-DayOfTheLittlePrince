package littleprince.item.command.application.service;

import littleprince.common.domain.StatusType;
import littleprince.common.exception.BusinessException;
import littleprince.item.Exception.ItemErrorCode;
import littleprince.item.command.domain.aggregate.entity.GetItem;
import littleprince.item.command.domain.aggregate.entity.Item;
import littleprince.item.command.domain.repository.GetItemRepository;
import littleprince.item.command.domain.repository.ItemRepository;
import littleprince.member.command.domain.aggregate.MemberDTO;
import littleprince.member.exception.MemberErrorCode;
import littleprince.member.query.mapper.MemberQueryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemCommandService {
    private final ItemRepository jpaItemRepository;
    private final MemberQueryMapper memberQueryMapper;
    private final GetItemRepository getItemRepository;

    // 사용자 아이템 추가
    @Transactional
    @Async
    public void addItem(Long memberId, int level) {
        log.info("=== addItem() 호출 ===" + memberId);
        MemberDTO member = memberQueryMapper.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.USER_NOT_FOUND));
        log.info("아이템 지급을 위한 유저 찾음!");

        Item newItem = jpaItemRepository.findByLevel(level);

        if (newItem == null) {
            throw new BusinessException(ItemErrorCode.ITEM_NOT_FOUND);
        }

        boolean alreadyExists = getItemRepository.existsByMemberIdAndItemId(memberId, newItem.getItemId());
        if (alreadyExists) return;

        GetItem getItem = GetItem.builder()
                .memberId(member.getMemberId())
                .itemId(newItem.getItemId())
                .isHidden(StatusType.N)
                .build();

        getItemRepository.save(getItem);
    }


    // 아이템 숨김 처리
    @Transactional
    public void toggleItemHidden(Long memberId, Long itemId) {
        memberQueryMapper.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.USER_NOT_FOUND));

        GetItem getItem = getItemRepository.findByMemberIdAndItemId(memberId, itemId)
                .orElseThrow(() -> new BusinessException(ItemErrorCode.ITEM_NOT_FOUND));

        getItem.setIsHidden(
                getItem.getIsHidden() == StatusType.Y ? StatusType.N : StatusType.Y
        );
    }

}
