package littleprince.item.command.application.service;

import littleprince.common.exception.BusinessException;
import littleprince.item.Exception.ItemErrorCode;
import littleprince.item.command.domain.aggregate.GetItem;
import littleprince.item.command.domain.aggregate.HiddenItem;
import littleprince.item.command.domain.aggregate.Item;
import littleprince.item.command.domain.repository.GetItemRepository;
import littleprince.item.command.domain.repository.ItemRepository;
import littleprince.item.query.mapper.ItemMapper;
import littleprince.member.command.application.repository.MemberRepository;
import littleprince.member.command.domain.aggregate.Member;
import littleprince.member.exception.MemberErrorCode;
import littleprince.member.query.mapper.MemberQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemCommandService {
    private final ItemRepository jpaItemRepository;
    private final MemberQueryMapper memberQueryMapper;
    private final MemberRepository memberRepository;
    private final GetItemRepository getItemRepository;
    private final ItemMapper itemMapper;

    // 사용자 아이템 추가
    public void addItem(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.USER_NOT_FOUND));

        int level = member.getLevel();

        Item newItem = jpaItemRepository.findByLevel(level);

        if (newItem == null) {
            throw new BusinessException(ItemErrorCode.ITEM_NOT_FOUND);
        }

        boolean alreadyExists = getItemRepository.existsByMemberIdAndItemId(memberId, newItem.getItemId());
        if (alreadyExists) return;

        GetItem getItem = GetItem.builder()
                .memberId(member.getMemberId())
                .itemId(newItem.getItemId())
                .isHidden(HiddenItem.N)
                .build();

        getItemRepository.save(getItem);
    }


    // 아이템 숨김 처리
    @Transactional
    public void toggleItemHidden(Long memberId, Long itemId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.USER_NOT_FOUND));

        GetItem getItem = getItemRepository.findByMemberIdAndItemId(memberId, itemId)
                .orElseThrow(() -> new BusinessException(ItemErrorCode.ITEM_NOT_FOUND));;

        getItem.setIsHidden(
                getItem.getIsHidden() == HiddenItem.Y ? HiddenItem.N : HiddenItem.Y
        );
    }

}
