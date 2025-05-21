package littleprince.review.command.domain.repository;

import littleprince.review.command.domain.aggregate.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.Optional;

public interface JpaReviewRepository extends JpaRepository<Review, Long> {
    Optional<Object> findByMemberIdAndReviewDate(Long memberId, Date date);
}
