CREATE TABLE IF NOT EXISTS feedback (
    id BIGSERIAL PRIMARY KEY,
    date_time TIMESTAMP NOT NULL,
    title VARCHAR(80) NOT NULL,
    description TEXT NOT NULL,
    user_id BIGINT NOT NULL,
    contractor_id BIGINT NOT NULL,
    reject_reason VARCHAR(200),
    feedback_status feedback_status NOT NULL,
    CONSTRAINT fk_feedback_users FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_feedback_contractor FOREIGN KEY (contractor_id) REFERENCES contractor(id)
    );