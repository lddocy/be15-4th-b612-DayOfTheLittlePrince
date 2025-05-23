package littleprince.plan.command.domain.aggregate;

import jakarta.persistence.*;
import littleprince.common.domain.StatusType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
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
    @Column(name = "is_checked")
    @Builder.Default
    private StatusType checked = StatusType.N;

    private LocalDate date;

    private LocalDateTime createdAt;

    @Builder
    public Task(Long memberId, Long projectId, String content, LocalDate date, StatusType checked) {
        this.memberId = memberId;
        this.projectId = projectId;
        this.content = content;
        this.date = date;
        this.checked = checked;
        this.createdAt = LocalDateTime.now();
    }

    public void toggleCheck() {
        this.checked = this.checked == StatusType.Y ? StatusType.N : StatusType.Y;
    }
}