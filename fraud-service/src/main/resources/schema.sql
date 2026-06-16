CREATE TABLE fraud_rules (
    id BIGSERIAL PRIMARY KEY,

    rule_code VARCHAR(100) UNIQUE,

    rule_name VARCHAR(255),

    enabled BOOLEAN,

    threshold_value NUMERIC(19,4),

    created_at TIMESTAMP
);