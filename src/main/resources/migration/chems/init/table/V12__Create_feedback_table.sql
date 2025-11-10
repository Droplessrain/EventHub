CREATE TABLE IF NOT EXISTS feedback (
    id BIGSERIAL PRIMARY KEY,
    dateTime TIMESTAMP NOT NULL,
    title VARCHAR(40) NOT NULL,
    description VARCHAR(400) NOT NULL,
    usersId BIGINT NOT NULL,
    contractorId BIGINT NOT NULL,
    rejectReason VARCHAR(40),
    status feedbackStatus NOT NULL,
    CONSTRAINT fk_feedback_users FOREIGN KEY (usersId) REFERENCES users(id),
    CONSTRAINT fk_feedback_contractor FOREIGN KEY (contractorId) REFERENCES contractor(id)
    );