CREATE TABLE saga_instances (
                                id BIGSERIAL PRIMARY KEY,
                                saga_id VARCHAR(100) UNIQUE,         -- global saga identifier
                                transaction_id VARCHAR(100),         -- Payment ID / business transaction
                                state VARCHAR(50),                    -- STARTED, COMPLETED, FAILED, COMPENSATED
                                created_at TIMESTAMP DEFAULT now(),
                                updated_at TIMESTAMP DEFAULT now()   -- add updated_at for tracking
);
CREATE TABLE saga_steps (
                            id BIGSERIAL PRIMARY KEY,
                            saga_id VARCHAR(100),                 -- FK to saga_instances.saga_id
                            step_name VARCHAR(100),               -- ACCOUNT_DEBIT, LEDGER_CREATE ...
                            status VARCHAR(30),                   -- PENDING, SUCCESS, FAILED, COMPENSATED
                            executed_at TIMESTAMP DEFAULT now(),
                            payload JSONB,                        -- optional: input/output for audit/compensation
                            retry_count INT DEFAULT 0             -- optional: track retries
);