CREATE TABLE IF NOT EXISTS contractor (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL CHECK(birth_date <= CURRENT_DATE - INTERVAL '18 years'),
    user_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_contractor_users FOREIGN KEY (user_id) REFERENCES users(id)
    );