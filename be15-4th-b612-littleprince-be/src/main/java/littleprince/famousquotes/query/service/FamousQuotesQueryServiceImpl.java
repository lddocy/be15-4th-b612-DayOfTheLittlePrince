package littleprince.famousquotes.query.service;

import littleprince.famousquotes.query.mapper.FamousQuotesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FamousQuotesQueryServiceImpl implements FamousQuotesQueryService {
    private final FamousQuotesMapper famousQuotesMapper;

    @Override
    public String getFamousQuotes() {
        return famousQuotesMapper.getRandomFamousQuote();
    }
}
