package littleprince.member.query.service;

import littleprince.common.exception.BusinessException;
import littleprince.member.exception.MemberErrorCode;
import littleprince.member.query.dto.response.MemberInfoResponse;
import littleprince.member.query.mapper.MemberQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService{
    private final MemberQueryMapper memberQueryMapper;

    @Override
    public MemberInfoResponse getMemberInfo(Long memberId) {
        MemberInfoResponse memberInfo = memberQueryMapper.findMemberByMemberId(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.USER_NOT_FOUND));

        return memberInfo;
    }

}
