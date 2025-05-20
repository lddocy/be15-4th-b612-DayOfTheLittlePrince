package littleprince.ai.query.service;

import littleprince.config.security.model.CustomUserDetail;
import littleprince.plan.query.dto.response.AiShortPlanDTO;
import littleprince.plan.query.dto.response.ShortListResponse;
import littleprince.plan.query.service.ShortListQueryService;
import littleprince.utils.DayOfWeekUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AiQueryServiceImpl implements AiQueryService {
    private final static String[] KOREAN_DAYS = {"월", "화", "수", "목", "금", "토", "일"};
    private final ChatClient chatClient;
    private final ShortListQueryService shortListQueryService;

    @Override
    public ShortListResponse generateAiPlan(CustomUserDetail userDetail) {
        Long memberId = userDetail.getMemberId();

        List<AiShortPlanDTO> shortPlanList = shortListQueryService.getMemberShortPlan(memberId);

        /* TODO: 가져온 리스트 길이가 너무 짧은 경우 리스트 요청 불가?*/

        List<List<String>> planList = new ArrayList<>();

        /* 리스트 초기화 해주기*/
        for (int i = 0; i < 7; i++) {
            planList.add(new ArrayList<>());
        }

        /* 각 날짜에 맞게 넣어 주기*/
        for (AiShortPlanDTO shortPlanDTO : shortPlanList) {
            int date = DayOfWeekUtils.dayToWeek(shortPlanDTO.getDate().toLocalDate());
            String content = shortPlanDTO.getContent();
            planList.get(date).add(content);
        }

        /* 요청 형식으로 만들기 */
        for(int i = 0; i < planList.size(); i++){
            StringBuilder sb = new StringBuilder();
            for(String content: planList.get(i)){
                sb.append(content).append(", ");
            }
            sb.append("\n");
            log.info("{}: {}", KOREAN_DAYS[i], sb.toString());
        }


        return null;
    }
}
