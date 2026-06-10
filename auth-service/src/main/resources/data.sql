INSERT INTO users (username, password_hash, created_at, updated_at)
VALUES
    ('alice', '$2a$12$D4G5f18o7aMMfwasBlG.Du1fCqZt8c0rWcKM9iN5yRqVxM/Po9KqG', now(), now()), -- password: "password123"
    ('bob',   '$2a$12$EIXChsXn6BZkmLwnRMEJmO6YhC9rR3LKmUFZ0Fl6cD1LxK2kaZbsa', now(), now()), -- password: "secret456"
    ('carol', '$2a$12$7qC5fVt1fC1NQY3lS/1h7uYpN5ZqXDzG9bK3p8Hr6a7K9Yb9gH0cO', now(), now()); -- password: "admin789"


INSERT INTO roles (name)
VALUES
    ('USER'),
    ('ADMIN'),
    ('AUDITOR');


INSERT INTO user_roles (user_id, role_id)
VALUES
-- Alice: normal user
(1, 1),

-- Bob: user + auditor
(2, 1),
(2, 3),

-- Carol: admin
(3, 2);


INSERT INTO refresh_tokens (user_id, token, expires_at, revoked, created_at)
VALUES
    (1, 'rt_alice_001', now() + interval '30 days', FALSE, now() - interval '1 day'),
    (2, 'rt_bob_001', now() + interval '30 days', FALSE, now() - interval '1 day'),
    (3, 'rt_carol_001', now() + interval '30 days', FALSE, now() - interval '1 day'),

-- revoked token
    (1, 'rt_alice_002', now() + interval '30 days', TRUE, now() - interval '10 days');