INSERT INTO audits (service_name, user_id, action, entity, entity_id, old_value, new_value, trace_id)
VALUES
-- Auth Service
('Auth Service', 1, 'LOGIN', 'User', 1, NULL, '{"status":"SUCCESS"}', gen_random_uuid()),
('Auth Service', 2, 'LOGIN', 'User', 2, NULL, '{"status":"FAILED","reason":"Wrong password"}', gen_random_uuid()),

-- Account Service
('Account Service', 1, 'UPDATE_BALANCE', 'Account', 1, '{"current_balance":1500.00}', '{"current_balance":1400.00}', gen_random_uuid()),
('Account Service', 2, 'UPDATE_BALANCE', 'Account', 3, '{"current_balance":50.00}', '{"current_balance":25.00}', gen_random_uuid()),
('Account Service', 3, 'CREATE_ACCOUNT', 'Account', 6, NULL, '{"account_type":"SAVINGS","current_balance":100.00}', gen_random_uuid()),

-- Payment Service
('Payment Service', 1, 'TRANSFER', 'Transaction', 101, '{"status":"PENDING"}', '{"status":"COMPLETED","amount":100.00,"from_account":1,"to_account":2}', gen_random_uuid()),
('Payment Service', 2, 'TRANSFER', 'Transaction', 102, '{"status":"PENDING"}', '{"status":"FAILED","reason":"Insufficient funds"}', gen_random_uuid()),

-- Fraud Service
('Fraud Service', 4, 'FLAG_TRANSACTION', 'Transaction', 103, '{"status":"COMPLETED"}', '{"status":"SUSPICIOUS"}', gen_random_uuid()),

-- Notification Service
('Notification Service', 1, 'SEND_EMAIL', 'Notification', 201, NULL, '{"status":"SENT","to":"user1@example.com"}', gen_random_uuid()),
('Notification Service', 2, 'SEND_SMS', 'Notification', 202, NULL, '{"status":"FAILED","to":"+123456789"}', gen_random_uuid());