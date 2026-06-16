INSERT INTO fraud_rules (name, condition, action)
VALUES
-- Rule 1: High amount transaction
('HIGH_AMOUNT', '{"amount":">100000000"}', 'FLAG'),

-- Rule 2: Very high amount → block luôn
('CRITICAL_AMOUNT', '{"amount":">500000000"}', 'BLOCK'),

-- Rule 3: Too many transactions per minute
('VELOCITY_LIMIT', '{"count_per_minute":">5"}', 'FLAG'),

-- Rule 4: Suspicious small rapid transfers (smurfing)
('SMURFING_PATTERN', '{"amount":"<1000000","count_per_10min":">10"}', 'FLAG'),

-- Rule 5: Foreign risky country simulation
('RISK_COUNTRY', '{"country":"LIST"}', 'BLOCK');

INSERT INTO fraud_flags (payment_id, rule_id, flagged_at, resolved)
VALUES
-- High amount flagged
(1001, 1, now() - interval '2 hours', FALSE),

-- Critical amount blocked case
(1002, 2, now() - interval '1 hour', FALSE),

-- Velocity anomaly
(1003, 3, now() - interval '30 minutes', FALSE),

-- Smurfing pattern detected
(1004, 4, now() - interval '10 minutes', FALSE),

-- Risk country transaction blocked
(1005, 5, now() - interval '5 minutes', FALSE),

-- Already resolved case (for testing workflow)
(1006, 1, now() - interval '1 day', TRUE);