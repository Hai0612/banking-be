package src.main.java.com.bank.ledger.event;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;

@Entity
@Table(name = "outbox_events")
@Getter
@NoArgsConstructor
public class OutboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aggregateType;

    private String aggregateId;

    private String eventType;

    @JdbcTypeCode(SqlTypes.JSON)
    private String payload;

    private String status;

    private Integer retryCount;

    private Instant createdAt;

    private Instant publishedAt;
}
