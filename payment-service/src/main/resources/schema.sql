CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,

    transaction_id UUID NOT NULL UNIQUE,

    from_account_id BIGINT NOT NULL,

    to_account_id BIGINT NOT NULL,

    amount NUMERIC(20,2) NOT NULL,

    currency CHAR(3) NOT NULL DEFAULT 'USD',

    status VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT now(),

    updated_at TIMESTAMP NOT NULL DEFAULT now(),

    version BIGINT NOT NULL DEFAULT 0
);v

-- Idempotency Key
CREATE TABLE payment_idempotency_keys (
    id BIGSERIAL PRIMARY KEY,

    idempotency_key VARCHAR(100)
        UNIQUE NOT NULL,

    payment_id BIGINT,

    status VARCHAR(20) NOT NULL,

    response JSONB,

    created_at TIMESTAMP DEFAULT now()
);

-- Outbox for eventsCREATE TABLE payment_outbox (

                        id BIGSERIAL PRIMARY KEY,

                        transaction_id UUID NOT NULL,

                        payment_id BIGINT NOT NULL,

                        event_type VARCHAR(50) NOT NULL,

                        payload JSONB NOT NULL,

                        status VARCHAR(20) NOT NULL,

                        retry_count INT DEFAULT 0,

                        created_at TIMESTAMP DEFAULT now(),

                        sent_at TIMESTAMP
                    );