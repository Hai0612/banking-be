INSERT INTO customers (user_id, full_name, email, phone, created_at, updated_at)
VALUES
    (1, 'Alice Nguyen', 'alice@gmail.com', '0901000001', now(), now()),
    (2, 'Bob Tran',    'bob@gmail.com',   '0901000002', now(), now()),
    (3, 'Carol Le',    'carol@gmail.com', '0901000003', now(), now());

    INSERT INTO kyc_documents (customer_id, doc_type, doc_number, status, submitted_at, verified_at)
    VALUES
        -- Alice: pending KYC
        (1, 'CCCD', '001200000001', 'PENDING', now(), NULL),

        -- Bob: verified
        (2, 'CCCD', '001200000002', 'VERIFIED', now() - interval '5 days', now() - interval '2 days'),

        -- Carol: rejected then resubmitted
        (3, 'PASSPORT', 'P123456789', 'REJECTED', now() - interval '10 days', now() - interval '7 days'),
        (3, 'PASSPORT', 'P123456789', 'PENDING', now(), NULL);