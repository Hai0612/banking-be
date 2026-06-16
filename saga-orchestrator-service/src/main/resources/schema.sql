CREATE TABLE saga_instances (
    id BIGSERIAL PRIMARY KEY,

    saga_id UUID NOT NULL,

    transfer_ref VARCHAR(100),

    status VARCHAR(30),

    started_at TIMESTAMP,

    completed_at TIMESTAMP
);
CREATE TABLE saga_steps (
    id BIGSERIAL PRIMARY KEY,

    saga_id UUID NOT NULL,

    service_name VARCHAR(100),

    step_name VARCHAR(100),

    status VARCHAR(30),

    retry_count INT DEFAULT 0,

    updated_at TIMESTAMP
);
CREATE TABLE inbox_events (
    id BIGSERIAL PRIMARY KEY,

    event_id VARCHAR(100) UNIQUE,

    event_type VARCHAR(100),

    processed BOOLEAN DEFAULT FALSE,

    processed_at TIMESTAMP
);