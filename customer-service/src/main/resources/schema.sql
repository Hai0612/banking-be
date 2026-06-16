CREATE TABLE customers (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);
CREATE TABLE kyc_documents (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    doc_type VARCHAR(50),
    doc_number VARCHAR(100),
    status VARCHAR(20) DEFAULT 'PENDING',
    submitted_at TIMESTAMP DEFAULT NOW(),
    verified_at TIMESTAMP
);

CREATE TABLE accounts (
                          id BIGSERIAL PRIMARY KEY,
                          customer_id BIGINT REFERENCES customers(id),
                          account_type VARCHAR(20) NOT NULL, -- SAVINGS, CURRENT
                          currency CHAR(3) NOT NULL DEFAULT 'USD',
                          current_balance NUMERIC(20, 2) NOT NULL DEFAULT 0,
                          status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
                          version BIGINT NOT NULL DEFAULT 0, -- Optimistic lock
                          created_at TIMESTAMP NOT NULL DEFAULT now(),
                          updated_at TIMESTAMP NOT NULL DEFAULT now()
);

-- Optional: account snapshot history
CREATE TABLE account_snapshots (
                                   id BIGSERIAL PRIMARY KEY,
                                   account_id BIGINT REFERENCES accounts(id),
                                   balance NUMERIC(20,2),
                                   snapshot_time TIMESTAMP DEFAULT now()
);

