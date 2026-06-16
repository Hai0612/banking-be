CREATE TABLE customers (
    id BIGSERIAL PRIMARY KEY,
    customer_no VARCHAR(30) UNIQUE NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(30),
    status VARCHAR(30),
    created_at TIMESTAMP NOT NULL
);
CREATE TABLE customer_kyc (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    kyc_status VARCHAR(30),
    verified_at TIMESTAMP,
    document_no VARCHAR(100)
);
CREATE TABLE accounts (
    id BIGSERIAL PRIMARY KEY,
    account_no VARCHAR(30) UNIQUE NOT NULL,
    customer_id BIGINT NOT NULL,

    available_balance NUMERIC(19,4) NOT NULL,
    hold_balance NUMERIC(19,4) NOT NULL,

    currency VARCHAR(10) NOT NULL,

    status VARCHAR(30),

    version BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL
);
CREATE TABLE account_transactions (
    id BIGSERIAL PRIMARY KEY,

    account_no VARCHAR(30) NOT NULL,

    transaction_ref VARCHAR(100) NOT NULL,

    transaction_type VARCHAR(30),

    amount NUMERIC(19,4),

    balance_before NUMERIC(19,4),

    balance_after NUMERIC(19,4),

    created_at TIMESTAMP NOT NULL
);
CREATE TABLE inbox_events (
    id BIGSERIAL PRIMARY KEY,

    event_id VARCHAR(100) UNIQUE,

    event_type VARCHAR(100),

    processed BOOLEAN DEFAULT FALSE,

    processed_at TIMESTAMP
);