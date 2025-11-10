CREATE TABLE IF NOT EXISTS contractor (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    user_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    region VARCHAR(50) NOT NULL,
    CONSTRAINT fk_contractor_users FOREIGN KEY (usersId) REFERENCES users(id)
    );

CREATE INDEX IF NOT EXISTS idx_contractor_users ON contractor (usersId);