package littleprince.item.command.domain.aggregate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "famous_quotes")
public class FamousQuotes {
    @Id
    private Long famousQuotesId;

    private String title;
}
