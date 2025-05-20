package littleprince.member.query.service;

import littleprince.config.security.model.CustomUserDetail;
import littleprince.member.query.dto.response.MemberInfoResponse;

public interface MemberQueryService {
    MemberInfoResponse getMemberInfo(CustomUserDetail userDetail);
}
