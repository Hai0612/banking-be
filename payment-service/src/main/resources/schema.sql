CREATE TABLE transfers (
    id BIGSERIAL PRIMARY KEY,

    transfer_ref VARCHAR(100) UNIQUE NOT NULL,

    source_account VARCHAR(30) NOT NULL,

    destination_account VARCHAR(30) NOT NULL,

    amount NUMERIC(19,4) NOT NULL,

    currency VARCHAR(10),

    status VARCHAR(30) NOT NULL,

    failure_reason TEXT,

    trace_id VARCHAR(100),

    created_at TIMESTAMP NOT NULL,

    completed_at TIMESTAMP
);
CREATE TABLE transfer_state_history (
    id BIGSERIAL PRIMARY KEY,

    transfer_id BIGINT NOT NULL,

    old_status VARCHAR(30),

    new_status VARCHAR(30),

    changed_at TIMESTAMP NOT NULL
);
CREATE TABLE payment_idempotency_keys (
    id BIGSERIAL PRIMARY KEY,

    idempotency_key VARCHAR(255) UNIQUE NOT NULL,

    request_hash VARCHAR(255) NOT NULL,

    transfer_id BIGINT,

    response_body JSONB,

    status VARCHAR(30),

    expired_at TIMESTAMP,

    created_at TIMESTAMP NOT NULL
);
CREATE TABLE outbox_events (
    id BIGSERIAL PRIMARY KEY,

    aggregate_type VARCHAR(100),

    aggregate_id VARCHAR(100),

    event_type VARCHAR(100),

    payload JSONB,

    status VARCHAR(30),

    retry_count INT DEFAULT 0,

    created_at TIMESTAMP,

    published_at TIMESTAMP
);
CREATE TABLE inbox_events (
    id BIGSERIAL PRIMARY KEY,

    event_id VARCHAR(100) UNIQUE,

    event_type VARCHAR(100),

    processed BOOLEAN DEFAULT FALSE,

    processed_at TIMESTAMP
);