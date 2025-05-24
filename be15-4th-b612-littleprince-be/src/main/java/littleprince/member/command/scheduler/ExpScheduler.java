package littleprince.member.command.scheduler;

import littleprince.member.command.application.service.MemberCommandService;
import littleprince.member.query.dto.MemberExpDTO;
import littleprince.member.query.mapper.ExpHistoryQueryMapper;
import littleprince.member.query.mapper.MemberQueryMapper;
import littleprince.plan.command.domain.aggregate.Project;
import littleprince.plan.command.domain.aggregate.Task;
import littleprince.plan.command.repository.ProjectRepository;
import littleprince.plan.command.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class ExpScheduler {

    private final MemberQueryMapper memberQueryMapper;
    private final MemberCommandService memberCommandService;
    private final ExpHistoryQueryMapper expHistoryQueryMapper;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    /* 매일 오전 12시 1분에 실행 */
    @Scheduled(cron = "0 1 0 * * *")
//@Scheduled(cron = "0 */1 * * * *") // 1분
//    @Scheduled(cron = "*/20 * * * * *") // 20초
    @Transactional
    public void giveDailyExp() {
        List<MemberExpDTO> members = memberQueryMapper.findAllExpInfo();

        // 오늘 날짜
        LocalDate today = LocalDate.now();

        LocalDate yesterday = today.minusDays(1);

        for (MemberExpDTO member : members) {

            Long memberId = member.getMemberId();

            if (expHistoryQueryMapper.existsTodayHistory(memberId)) continue;

            List<Project> projectOpt = projectRepository.findAllByMemberIdAndEndDate(memberId, yesterday);

            if (projectOpt.isEmpty()) continue;

            for (Project project : projectOpt) {
                List<Task> taskList = taskRepository.findAllByProjectIdAndDate(project.getProjectId(), yesterday);

                int totalExp = Math.min(expHistoryQueryMapper.getTaskExp(member.getMemberId()), 10);
                log.info("memberId = {}", member.getMemberId());
                log.info("totalExp = {}", totalExp);

                memberCommandService.addExp(member.getMemberId(), totalExp);

                for (Task task : taskList) {
                    if (task.getProjectId() != null) {
                        Long taskProjectId = task.getProjectId();
                        int projectExp = expHistoryQueryMapper.getProjectExp(member.getMemberId(), taskProjectId);
                        Long projectId = expHistoryQueryMapper.getProjectIdExp(member.getMemberId(), taskProjectId);
                        log.info("memberId = {}", member.getMemberId());
                        log.info("totalExp = {}", totalExp);
                        memberCommandService.addExp(member.getMemberId(), projectExp, projectId);
                    }
                }
            }
        }
    }
}

