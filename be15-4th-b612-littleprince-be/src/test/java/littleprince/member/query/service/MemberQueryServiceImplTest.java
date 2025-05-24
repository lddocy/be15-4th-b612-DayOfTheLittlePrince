package littleprince.member.query.service;

import littleprince.common.exception.BusinessException;
import littleprince.member.exception.MemberErrorCode;
import littleprince.member.query.dto.response.MemberInfoResponse;
import littleprince.member.query.mapper.MemberQueryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberQueryServiceImplTest {

    @Mock
    private MemberQueryMapper memberQueryMapper;

    @InjectMocks
    private MemberQueryServiceImpl memberQueryService;

    private final Long memberId = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMemberInfo_성공적으로_회원정보를_가져온다() {
        // given
        MemberInfoResponse expected = MemberInfoResponse.builder()
                .memberId(memberId)
                .exp(100)
                .level(3)
                .planetName("B-612")
                .isSelected("Y")
                .badgeName("용기 있는 여행자")
                .build();

        when(memberQueryMapper.findMemberByMemberId(memberId)).thenReturn(Optional.of(expected));

        // when
        MemberInfoResponse result = memberQueryService.getMemberInfo(memberId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getMemberId()).isEqualTo(memberId);
        assertThat(result.getPlanetName()).isEqualTo("B-612");
        assertThat(result.getBadgeName()).contains("여행자");
    }

    @Test
    void getMemberInfo_회원이_없으면_예외가_발생한다() {
        // given
        when(memberQueryMapper.findMemberByMemberId(memberId)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> memberQueryService.getMemberInfo(memberId))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(MemberErrorCode.USER_NOT_FOUND.getMessage());
    }
}
