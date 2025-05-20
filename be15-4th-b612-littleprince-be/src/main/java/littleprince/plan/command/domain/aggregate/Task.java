package littleprince.plan.command.domain.aggregate;

import jakarta.persistence.*;
import littleprince.common.domain.StatusType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private Long memberId;

    private Long projectId; // 단기는 null

    private String content;

    @Enumerated(EnumType.STRING)
    private StatusType isChecked;

    private LocalDate date;

    private LocalDateTime createdAt;

    @Builder
    public Task(Long memberId, Long projectId, String content, LocalDate date) {
        this.memberId = memberId;
        this.projectId = projectId;
        this.content = content;
        this.date = date;
        this.isChecked = StatusType.N;
        this.createdAt = LocalDateTime.now();
    }

    public void toggleCheck() {
        this.isChecked = this.isChecked == StatusType.Y ? StatusType.N : StatusType.Y;
    }
}
