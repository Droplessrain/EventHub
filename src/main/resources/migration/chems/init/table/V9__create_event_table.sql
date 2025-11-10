CREATE TABLE IF NOT EXISTS event (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(80) NOT NULL,
    event_type event_type NOT NULL,
    date TIMESTAMP NOT NULL,
    total_time SMALLINT NOT NULL CHECK (total_time > 0),
    description TEXT NOT NULL,
    CONSTRAINT fk_event_users FOREIGN KEY (user_id) REFERENCES users(id)
    );