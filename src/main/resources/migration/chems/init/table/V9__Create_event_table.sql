CREATE TABLE IF NOT EXISTS event (
    id BIGSERIAL PRIMARY KEY,
    users_id BIGINT NOT NULL,
    title VARCHAR(20) NOT NULL,
    event_type EVENT_TYPE NOT NULL,
    date TIMESTAMP NOT NULL,
    total_time SMALLINT NOT NULL CHECK (total_time > 0),
    description VARCHAR(5000) NOT NULL,
    CONSTRAINT fk_event_users FOREIGN KEY (users_id) REFERENCES users(id)
    );