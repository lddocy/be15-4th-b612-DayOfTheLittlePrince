package littleprince.member.command.domain.aggregate;

import jakarta.persistence.*;
import littleprince.common.domain.StatusType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "member")
@Getter
@Setter(AccessLevel.PACKAGE)
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

    public void changePassword(String encryptPassword){
        this.password = encryptPassword;
    }

    public void changePlanetName(String planetName) {
        this.planetName = planetName;
    }

}
