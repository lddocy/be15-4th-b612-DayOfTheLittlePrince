package littleprince.review.command.domain.repository;

import littleprince.review.command.domain.aggregate.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReviewRepository extends JpaRepository<Review, Long> {
}
