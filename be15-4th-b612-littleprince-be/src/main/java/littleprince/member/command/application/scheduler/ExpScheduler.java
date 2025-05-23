package littleprince.member.command.application.scheduler;

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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class ExpScheduler {

    private final MemberQueryMapper memberQueryMapper;
    private final MemberCommandService memberCommandService;
    private final ExpHistoryQueryMapper expHistoryQueryMapper;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Scheduled(cron = "*/10 * * * * *")
    @Transactional
    public void giveDailyExp() {
        List<MemberExpDTO> members = memberQueryMapper.findAllExpInfo();

        System.out.println(members.toString());

        System.out.println("30초 지남 시작");

        // 오늘 날짜
        LocalDate today = LocalDate.now();

//        LocalDate yesterday = LocalDate.now().minusDays(1);
//
//        int count = 0;
//
//        for (MemberExpDTO member : members) {
//
//            Long memberId = member.getMemberId();
//            System.out.println("memberId = " + memberId);
//
//            System.out.println("여기 왔다 1");
//            if (expHistoryQueryMapper.existsTodayHistory(memberId)) continue;
//
//            List<Project> projectOpt = projectRepository.findAllByMemberIdAndEndDate(memberId, yesterday);
//            for(Project project : projectOpt){
//                System.out.println(project.getProjectId() + " " + project.getTitle());
//            }
//            System.out.println("여기왔다 2");
//            if (projectOpt.isEmpty()) continue;
//            System.out.println("여기왔다 3");
//
//            Project project = projectOpt.get(count++);
//            System.out.println("여기왔다 4");
//            List<Task> task = taskRepository.findAllByProjectIdAndDate(project.getProjectId(), yesterday);
//            for (Task task1 : task) {
//                System.out.println("task1 = " + task1.getProjectId());
//            }
//            System.out.println("여기왔다 5");
//
//            int totalExp = Math.min(expHistoryQueryMapper.getTaskExp(memberId), 10);
//            System.out.println("total = " + totalExp);
//            memberCommandService.addExp(memberId, totalExp);
//
//            System.out.println("여기왔다 6 ");
//
//            for (Task t : task) {
//                if (t.getProjectId() != null) {
//                    System.out.println(t.getProjectId());
//                    Long taskProjectId = t.getProjectId();
//                    int projectExp = expHistoryQueryMapper.getProjectExp(memberId, taskProjectId);
//                    Long projectId = expHistoryQueryMapper.getProjectIdExp(memberId, taskProjectId);
//                    System.out.println("projectExp = " + projectExp);
//                    System.out.println("projectId = " + projectId);
//                    memberCommandService.addExp(memberId, projectExp, projectId);
//                }
//            }
//        }
    }
}

