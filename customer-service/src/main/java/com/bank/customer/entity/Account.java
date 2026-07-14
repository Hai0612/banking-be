package com.bank.customer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bank.customer.enums.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_no", unique = true, nullable = false, length = 30)
    private String accountNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false, length = 30)
    private AccountType accountType;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "available_balance", nullable = false, precision = 19, scale = 4)
    private BigDecimal availableBalance;

    @Column(name = "hold_balance", nullable = false, precision = 19, scale = 4)
    private BigDecimal holdBalance;

    @Column(name = "currency", nullable = false, length = 10)
    private String currency;

    @Column(name = "status", length = 30)
    private String status;

    @Version
    @Column(name = "version")
    private Long version;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<AccountTransaction> transactions;
}
