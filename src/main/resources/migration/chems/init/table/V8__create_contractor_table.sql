CREATE TABLE IF NOT EXISTS contractor (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birthdate DATE NOT NULL,
    users_id BIGINT NOT NULL,
    description VARCHAR(5000) NOT NULL,
    region VARCHAR(50) NOT NULL,
    CONSTRAINT fk_contractor_users FOREIGN KEY (usersId) REFERENCES users(id)
    );

CREATE INDEX IF NOT EXISTS idx_contractor_users ON contractor (usersId);