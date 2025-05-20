package littleprince.member.command.domain.aggregate;

import jakarta.persistence.*;
import littleprince.common.domain.StatusType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true)
    private String email;

    private String password;

    private int level;

    private int exp;

    private String planetName;

    @Enumerated(EnumType.STRING)
    private StatusType isNotificationEnabled;

    @Enumerated(EnumType.STRING)
    private StatusType isDeleted;

    private LocalDate createdAt;
}
