package littleprince.member.query.service;

import littleprince.common.exception.BusinessException;
import littleprince.config.security.model.CustomUserDetail;
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
    public MemberInfoResponse getMemberInfo(CustomUserDetail userDetail) {
        if(userDetail == null){
            throw new BusinessException(MemberErrorCode.USER_NOT_FOUND);
        }

        Long memberId = userDetail.getMemberId();

        MemberInfoResponse memberInfo = memberQueryMapper.findMemberByMemberId(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.USER_NOT_FOUND));

        return memberInfo;
    }

}
