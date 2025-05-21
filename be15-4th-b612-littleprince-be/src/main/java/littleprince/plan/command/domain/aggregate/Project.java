package littleprince.plan.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private Long memberId;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    @Builder
    public Project(Long memberId, String title, LocalDate startDate, LocalDate endDate) {
        this.memberId = memberId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}