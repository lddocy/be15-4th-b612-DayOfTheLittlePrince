package littleprince.member.query.service;

import littleprince.member.query.dto.response.MemberInfoResponse;

public interface MemberQueryService {
    MemberInfoResponse getMemberInfo(Long memberId);
}
