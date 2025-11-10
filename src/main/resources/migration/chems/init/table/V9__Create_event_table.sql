CREATE TABLE IF NOT EXISTS event (
    id BIGSERIAL PRIMARY KEY,
    usersId BIGINT NOT NULL,
    title VARCHAR(20) NOT NULL,
    eventType eventType NOT NULL,
    date TIMESTAMP NOT NULL,
    totalTime SMALLINT NOT NULL,
    description VARCHAR(5000) NOT NULL,
    CONSTRAINT fk_event_users FOREIGN KEY (usersId) REFERENCES users(id)
    );