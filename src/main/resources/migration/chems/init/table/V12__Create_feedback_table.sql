CREATE TABLE IF NOT EXISTS feedback (
    id BIGSERIAL PRIMARY KEY,
    date_time TIMESTAMP NOT NULL,
    title VARCHAR(40) NOT NULL,
    description VARCHAR(400) NOT NULL,
    users_id BIGINT NOT NULL,
    contractor_id BIGINT NOT NULL,
    rejectReason VARCHAR(40),
    status FEEDBACK_STATUS NOT NULL,
    CONSTRAINT fk_feedback_users FOREIGN KEY (users_id) REFERENCES users(id),
    CONSTRAINT fk_feedback_contractor FOREIGN KEY (contractor_id) REFERENCES contractor(id)
    );