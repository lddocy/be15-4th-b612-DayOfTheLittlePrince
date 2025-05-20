package littleprince.ai.query.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import littleprince.ai.query.dto.response.GeneratePlaneResponse;
import littleprince.ai.query.exception.AiErrorCode;
import littleprince.common.exception.BusinessException;
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

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AiQueryServiceImpl implements AiQueryService {
    private final static String[] KOREAN_DAYS = {"월", "화", "수", "목", "금", "토", "일"};
    private final ChatClient chatClient;
    private final ShortListQueryService shortListQueryService;

    @Override
    public GeneratePlaneResponse generateAiPlan(CustomUserDetail userDetail) {
        final int DAYS_IN_WEEK = 7;
        Long memberId = userDetail.getMemberId();

        List<AiShortPlanDTO> shortPlanList = shortListQueryService.getMemberShortPlan(memberId);

        if (shortPlanList.size() < 5) {
            throw new BusinessException(AiErrorCode.TODO_HISTORY_TOO_SHORT);
        }

        List<List<String>> planList = new ArrayList<>(Collections.nCopies(DAYS_IN_WEEK, null));
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            planList.set(i, new ArrayList<>());
        }

        for (AiShortPlanDTO dto : shortPlanList) {
            int dayIndex = DayOfWeekUtils.dayToWeek(dto.getDate().toLocalDate());
            planList.get(dayIndex).add(dto.getContent());
        }

        StringBuilder aiRequest = new StringBuilder();
        for (int i = 0; i < planList.size(); i++) {
            String joined = String.join(", ", planList.get(i));
            aiRequest.append(KOREAN_DAYS[i]).append(": ").append(joined).append("\n");
            log.info("{}: {}", KOREAN_DAYS[i], joined);
        }

        aiRequest.append("\n\n할 일 10개 알려줘.");
        log.info("\n======= AI 요청 =======\n{}\n======================", aiRequest);

        String generateResult = chatClient.prompt()
                .system("너는 사용자의 요일별 투두 기록을 기반으로 다음 작성할 투두 리스트 내용을 추천하는 비서야. 출력은 JSON이며 key는 'task'야.")
                .user(aiRequest.toString())
                .call()
                .content();

        log.info("AI 응답: {}", generateResult);

        JsonNode rootNode = parseJson(generateResult);
        List<String> generatePlanList = new ArrayList<>();

        JsonNode taskArray = rootNode.get("task");
        if (taskArray != null && taskArray.isArray()) {
            for (JsonNode task : taskArray) {
                generatePlanList.add(task.asText());
            }
        }

        return GeneratePlaneResponse.builder()
                .generatePlanList(generatePlanList)
                .build();
    }

    private JsonNode parseJson(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            log.error("JSON 파싱 실패: {}", e.getMessage());
            throw new BusinessException(AiErrorCode.AI_PLAN_GENERATION_FAILED);
        }
    }
}
