INSERT INTO ledger_entries (payment_id, account_id, type, amount, currency)
VALUES
-- Payment 1001: normal transfer $100 from account 1 to 2
(1001, 1, 'DEBIT', 100.00, 'USD'),
(1001, 2, 'CREDIT', 100.00, 'USD'),

-- Payment 1002: critical transfer $500,000, blocked (still recorded for audit)
(1002, 3, 'DEBIT', 500000.00, 'USD'),
(1002, 4, 'CREDIT', 500000.00, 'USD'),

-- Payment 1003: velocity test, multiple small transfers
(1003, 1, 'DEBIT', 50.00, 'USD'),
(1003, 2, 'CREDIT', 50.00, 'USD'),

-- Payment 1004: smurfing pattern, multiple tiny transfers
(1004, 5, 'DEBIT', 1000.00, 'USD'),
(1004, 6, 'CREDIT', 1000.00, 'USD'),

-- Payment 1005: blocked due to risk country
(1005, 7, 'DEBIT', 2000.00, 'USD'),
(1005, 8, 'CREDIT', 2000.00, 'USD'),

-- Payment 1006: resolved fraud flag, normal entry
(1006, 1, 'DEBIT', 150.00, 'USD'),
(1006, 3, 'CREDIT', 150.00, 'USD');


INSERT INTO ledger_snapshots (account_id, balance, snapshot_time)
VALUES
    (1, 1500.00 - 100.00 - 50.00 - 150.00, now()),  -- account 1
    (2, 500.00 + 100.00 + 50.00, now()),           -- account 2
    (3, 3000.00 - 500000.00 + 150.00, now()),      -- account 3
    (4, 1200.00 + 500000.00, now()),               -- account 4
    (5, 0.00 - 1000.00, now()),                    -- account 5
    (6, 999.99 + 1000.00, now()),                  -- account 6
    (7, 2500.00 - 2000.00, now()),                 -- account 7
    (8, 10000.00 + 2000.00, now()),                -- account 8
    (9, 50.00, now()),                              -- account 9 untouched
    (10, 800.00, now());                            -- account 10 untouched