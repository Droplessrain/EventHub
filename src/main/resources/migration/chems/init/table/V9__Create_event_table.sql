CREATE TABLE IF NOT EXISTS event (
    id BIGSERIAL PRIMARY KEY,
    users_id BIGINT NOT NULL,
    title VARCHAR(20) NOT NULL,
    event_type eventType NOT NULL,
    date TIMESTAMP NOT NULL,
    total_time SMALLINT NOT NULL,
    description VARCHAR(5000) NOT NULL,
    CONSTRAINT fk_event_users FOREIGN KEY (users_id) REFERENCES users(id)
    );