CREATE TABLE ledger_accounts (
    id BIGSERIAL PRIMARY KEY,

    account_code VARCHAR(30) UNIQUE,

    account_name VARCHAR(255),

    account_type VARCHAR(30)
);
CREATE TABLE journal_entries (
    id BIGSERIAL PRIMARY KEY,

    journal_ref VARCHAR(100) UNIQUE,

    transaction_ref VARCHAR(100),

    status VARCHAR(30),

    created_at TIMESTAMP
);
CREATE TABLE journal_lines (
    id BIGSERIAL PRIMARY KEY,

    journal_entry_id BIGINT NOT NULL,

    ledger_account_id BIGINT NOT NULL,

    debit NUMERIC(19,4),

    credit NUMERIC(19,4)
);
CREATE TABLE inbox_events (
    id BIGSERIAL PRIMARY KEY,

    event_id VARCHAR(100) UNIQUE,

    event_type VARCHAR(100),

    processed BOOLEAN DEFAULT FALSE,

    processed_at TIMESTAMP
);