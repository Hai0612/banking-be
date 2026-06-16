-- =====================================================
-- LEDGER ENTRIES
-- Double-entry bookkeeping
-- Immutable (append-only)
-- =====================================================

CREATE TABLE ledger_entries (

    id BIGSERIAL PRIMARY KEY,

    transaction_id UUID NOT NULL,

    account_id BIGINT NOT NULL,

    direction VARCHAR(10) NOT NULL,

    amount NUMERIC(20,2) NOT NULL,

    currency CHAR(3) NOT NULL,

    description VARCHAR(255),

    created_at TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT chk_ledger_direction
        CHECK (direction IN ('DEBIT','CREDIT'))
);

-- Prevent duplicate ledger entries caused by retry/saga replay
CREATE UNIQUE INDEX uq_ledger_tx_account_direction
ON ledger_entries (
    transaction_id,
    account_id,
    direction
);

CREATE INDEX idx_ledger_transaction
ON ledger_entries(transaction_id);

CREATE INDEX idx_ledger_account
ON ledger_entries(account_id);

CREATE INDEX idx_ledger_created_at
ON ledger_entries(created_at);


-- =====================================================
-- LEDGER SNAPSHOTS
-- Cached balance for fast reconciliation/reporting
-- =====================================================

CREATE TABLE ledger_snapshots (

    id BIGSERIAL PRIMARY KEY,

    account_id BIGINT NOT NULL,

    balance NUMERIC(20,2) NOT NULL,

    snapshot_time TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_snapshot_account
ON ledger_snapshots(account_id);

CREATE INDEX idx_snapshot_time
ON ledger_snapshots(snapshot_time);


-- =====================================================
-- AUDIT LOG
-- Who did what, when, on which entity
-- =====================================================

CREATE TABLE audits (

    id BIGSERIAL PRIMARY KEY,

    transaction_id UUID,

    service_name VARCHAR(50) NOT NULL,

    user_id BIGINT,

    action VARCHAR(50) NOT NULL,

    entity VARCHAR(50),

    entity_id BIGINT,

    status VARCHAR(20),

    old_value JSONB,

    new_value JSONB,

    trace_id UUID,

    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_audit_transaction
ON audits(transaction_id);

CREATE INDEX idx_audit_trace
ON audits(trace_id);

CREATE INDEX idx_audit_created_at
ON audits(created_at);


-- =====================================================
-- RECONCILIATION RUNS
-- One batch execution record
-- =====================================================

CREATE TABLE reconciliation_runs (

    id BIGSERIAL PRIMARY KEY,

    run_date DATE NOT NULL,

    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',

    total_accounts_checked BIGINT DEFAULT 0,

    discrepancy_count INT DEFAULT 0,

    created_at TIMESTAMP NOT NULL DEFAULT now(),

    completed_at TIMESTAMP
);

CREATE INDEX idx_reconciliation_run_date
ON reconciliation_runs(run_date);


-- =====================================================
-- RECONCILIATION DISCREPANCIES
-- Accounts with mismatch between
-- Core Banking balance and Ledger balance
-- =====================================================

CREATE TABLE reconciliation_discrepancies (

    id BIGSERIAL PRIMARY KEY,

    reconciliation_run_id BIGINT NOT NULL,

    account_id BIGINT NOT NULL,

    ledger_balance NUMERIC(20,2) NOT NULL,

    account_balance NUMERIC(20,2) NOT NULL,

    difference NUMERIC(20,2) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT fk_reconciliation_run
        FOREIGN KEY (reconciliation_run_id)
        REFERENCES reconciliation_runs(id)
);

CREATE INDEX idx_reconciliation_account
ON reconciliation_discrepancies(account_id);

CREATE INDEX idx_reconciliation_run
ON reconciliation_discrepancies(reconciliation_run_id);


-- =====================================================
-- OUTBOX EVENTS
-- Transactional Outbox Pattern
-- Used to publish Kafka events safely
-- =====================================================

CREATE TABLE outbox_events (

    id BIGSERIAL PRIMARY KEY,

    aggregate_type VARCHAR(50) NOT NULL,

    aggregate_id VARCHAR(100) NOT NULL,

    event_type VARCHAR(100) NOT NULL,

    payload JSONB NOT NULL,

    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',

    retry_count INT NOT NULL DEFAULT 0,

    created_at TIMESTAMP NOT NULL DEFAULT now(),

    published_at TIMESTAMP
);

CREATE INDEX idx_outbox_status
ON outbox_events(status);

CREATE INDEX idx_outbox_created_at
ON outbox_events(created_at);