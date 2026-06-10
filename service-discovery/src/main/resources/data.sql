INSERT INTO customers (user_id, full_name, email, phone)
VALUES
    (1, 'Nguyen Van An', 'an.nguyen@example.com', '+84901234567'),
    (2, 'Tran Thi Bich', 'bich.tran@example.com', '+84907654321'),
    (3, 'Le Hoang Nam', 'nam.le@example.com', '+84901112233'),
    (4, 'Pham Minh Chau', 'chau.pham@example.com', '+84904445566'),
    (5, 'Vo Quoc Bao', 'bao.vo@example.com', '+84903332211');

INSERT INTO kyc_documents (customer_id, doc_type, doc_number, status, submitted_at, verified_at)
VALUES
-- Customer 1 (VERIFIED)
(1, 'PASSPORT', 'P123456789', 'VERIFIED', now() - interval '10 days', now() - interval '9 days'),

-- Customer 2 (PENDING)
(2, 'NATIONAL_ID', 'ID987654321', 'PENDING', now() - interval '2 days', NULL),

-- Customer 3 (VERIFIED)
(3, 'PASSPORT', 'P112233445', 'VERIFIED', now() - interval '20 days', now() - interval '18 days'),

-- Customer 4 (REJECTED)
(4, 'NATIONAL_ID', 'ID556677889', 'REJECTED', now() - interval '5 days', NULL),

-- Customer 5 (VERIFIED)
(5, 'DRIVER_LICENSE', 'DL99887766', 'VERIFIED', now() - interval '15 days', now() - interval '14 days');