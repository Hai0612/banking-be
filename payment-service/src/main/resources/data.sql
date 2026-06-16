INSERT INTO payments (from_account_id, to_account_id, amount, currency, status, version)
VALUES
-- Successful transfers
(1, 2, 100.00, 'USD', 'COMPLETED', 1),
(2, 3, 50.00, 'USD', 'COMPLETED', 1),

-- Pending (Saga in progress)
(3, 4, 500.00, 'USD', 'PENDING', 0),
(4, 5, 1000.00, 'USD', 'PENDING', 0),

-- Failed due to fraud / insufficient funds
(5, 6, 2000.00, 'USD', 'FAILED', 1),
(6, 7, 999999.00, 'USD', 'FAILED', 2),

-- Compensated (Saga rollback completed)
(7, 8, 300.00, 'USD', 'COMPENSATED', 2),

-- Edge case: high concurrency test
(1, 3, 10.00, 'USD', 'PENDING', 0),
(1, 3, 10.00, 'USD', 'PENDING', 0),
(1, 3, 10.00, 'USD', 'PENDING', 0);

INSERT INTO payment_idempotency_keys (idempotency_key, payment_id, status, response)
VALUES
    ('req-001', 1, 'SUCCESS', '{"message":"transfer completed","payment_id":1}'),
    ('req-002', 2, 'SUCCESS', '{"message":"transfer completed","payment_id":2}'),

-- retry same request → should NOT create new payment
    ('req-003', 3, 'IN_PROGRESS', NULL),

    ('req-004', 5, 'FAILED', '{"error":"fraud detected"}'),

    ('req-duplicate-001', 1, 'SUCCESS', '{"cached":true,"payment_id":1}');



INSERT INTO payment_outbox (payment_id, event_type, payload, status, created_at, sent_at)
VALUES
-- Payment success events
(1, 'PAYMENT_COMPLETED',
 '{"payment_id":1,"from":1,"to":2,"amount":100.00}', 'SENT', now() - interval '2 hours', now() - interval '2 hours'),

(2, 'PAYMENT_COMPLETED',
 '{"payment_id":2,"from":2,"to":3,"amount":50.00}', 'SENT', now() - interval '2 hours', now() - interval '2 hours'),

-- Pending events waiting Kafka publish
(3, 'PAYMENT_REQUESTED',
 '{"payment_id":3,"amount":500.00}', 'PENDING', now() - interval '10 minutes', NULL),

(4, 'PAYMENT_REQUESTED',
 '{"payment_id":4,"amount":1000.00}', 'PENDING', now() - interval '5 minutes', NULL),

-- Failed event (retry Kafka consumer)
(5, 'PAYMENT_FAILED',
 '{"payment_id":5,"reason":"fraud detected"}', 'FAILED', now() - interval '1 hour', NULL),

-- Compensated saga event
(7, 'PAYMENT_COMPENSATED',
 '{"payment_id":7,"reason":"saga rollback"}', 'SENT', now() - interval '30 minutes', now() - interval '29 minutes'),

-- High concurrency simulation
(8, 'PAYMENT_REQUESTED',
 '{"payment_id":8,"amount":10.00}', 'PENDING', now(), NULL),
(9, 'PAYMENT_REQUESTED',
 '{"payment_id":9,"amount":10.00}', 'PENDING', now(), NULL),
(10, 'PAYMENT_REQUESTED',
 '{"payment_id":10,"amount":10.00}', 'PENDING', now(), NULL);