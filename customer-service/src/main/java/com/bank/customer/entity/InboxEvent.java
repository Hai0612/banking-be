package com.bank.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "inbox_events")
public class InboxEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", unique = true, length = 100)
    private String eventId;

    @Column(name = "event_type", length = 100)
    private String eventType;

    @Column(name = "processed")
    private Boolean processed = false;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;
}