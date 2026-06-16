-- User table
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT now(),
                       updated_at TIMESTAMP NOT NULL DEFAULT now()
);

-- Role table
CREATE TABLE roles (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL
);

-- User-Roles mapping
CREATE TABLE user_roles (
                            user_id BIGINT REFERENCES users(id),
                            role_id BIGINT REFERENCES roles(id),
                            PRIMARY KEY(user_id, role_id)
);

-- Refresh Token
CREATE TABLE refresh_tokens (
                                id BIGSERIAL PRIMARY KEY,
                                user_id BIGINT REFERENCES users(id),
                                token VARCHAR(255) UNIQUE NOT NULL,
                                expires_at TIMESTAMP NOT NULL,
                                revoked BOOLEAN NOT NULL DEFAULT FALSE,
                                created_at TIMESTAMP NOT NULL DEFAULT now()
);