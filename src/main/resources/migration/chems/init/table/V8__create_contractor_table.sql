CREATE TABLE IF NOT EXISTS contractor (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL CHECK(AGE(birth_date)>18),
    user_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_contractor_users FOREIGN KEY (usersId) REFERENCES users(id)
    );

CREATE INDEX IF NOT EXISTS idx_contractor_users ON contractor (usersId);