package littleprince.famousquotes.query.service;

import littleprince.famousquotes.query.mapper.FamousQuotesMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class FamousQuotesQueryServiceImplTest {

    @Mock
    private FamousQuotesMapper famousQuotesMapper;

    @InjectMocks
    private FamousQuotesQueryServiceImpl famousQuotesQueryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 명언을_정상적으로_반환한다() {
        // given
        String expectedQuote = "가장 중요한 것은 눈에 보이지 않아.";
        when(famousQuotesMapper.getRandomFamousQuote()).thenReturn(expectedQuote);

        // when
        String actualQuote = famousQuotesQueryService.getFamousQuotes();

        // then
        assertThat(actualQuote).isEqualTo(expectedQuote);
    }
}
