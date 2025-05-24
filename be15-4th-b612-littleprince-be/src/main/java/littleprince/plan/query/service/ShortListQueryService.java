package littleprince.plan.query.service;

import littleprince.plan.query.dto.response.*;
import littleprince.plan.query.mapper.ShortListQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShortListQueryService {

    private final ShortListQueryMapper shortListQueryMapper;

    /* 단기 리스트 조회 */
    public ShortListResponse getShortList(Long memberId, Date date) {
        List<ShortListDTO> listDTO = shortListQueryMapper.getShortList(memberId, date);

        return ShortListResponse.builder()
                .shortList(listDTO)
                .build();
    }

    // 모든 단기 리스트 조회
    public ShortListsAllResponse getShortListsAll(Long memberId) {
        List<ShortListsAllDTO> listAllDTO = shortListQueryMapper.getShortListsAll(memberId);

        return ShortListsAllResponse.builder()
                .shortListsAll(listAllDTO)
                .build();
    }

    /* 단기 플랜 여부 조회 */
    public ShortPlanDateResponse getShortDates(Long memberId) {
        List<ShortPlanDateDTO> planDateDTO = shortListQueryMapper.getShortDates(memberId);

        return ShortPlanDateResponse.builder()
                .planDateDTO(planDateDTO)
                .build();
    }

    /* Ai에서 사용할 사용자의 단기 리스트 전체 조회 */
    public List<AiShortPlanDTO> getMemberShortPlan(Long memberId) {
        return shortListQueryMapper.getShortPlanByMemberId(memberId);
    }

    /* 오늘의 할 일 알림  */
    public String getTodayTasksText(Long memberId, Date date) {
        List<ShortListDTO> list = shortListQueryMapper.getShortList(memberId, date);

        if (list.isEmpty()) {
            return "오늘의 할 일이 없습니다.";
        }

        return list.stream()
                .map(task -> "• " + task.getContent())
                .collect(Collectors.joining("\n"));
    }

    /*달성하지 못한 단기 리스트 조회 */
    public long countUncheckedTasks(Long memberId, Date date) {
        return shortListQueryMapper.countUncheckedTasks(memberId, date);
    }

}