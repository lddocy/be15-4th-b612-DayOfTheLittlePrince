package littleprince.review.command.domain.aggregate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private Long memberId;

    private Date rivewDate;

    @CreatedDate
    private Timestamp createdAt;
}
