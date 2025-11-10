CREATE TABLE IF NOT EXISTS contractor (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    birthdate TIMESTAMP NOT NULL,
    usersId BIGINT NOT NULL,
    description VARCHAR(5000) NOT NULL,
    tower VARCHAR(50) NOT NULL,
    CONSTRAINT fk_contractor_users FOREIGN KEY (usersId) REFERENCES users(id)
    );

CREATE INDEX IF NOT EXISTS idx_contractor_users ON contractor (usersId);