CREATE TABLE IF NOT EXISTS complaint (
    id BIGSERIAL PRIMARY KEY,
    date_time TIMESTAMP NOT NULL,
    title VARCHAR(40) NOT NULL,
    description VARCHAR(400) NOT NULL,
    users_id BIGINT NOT NULL,
    contractor_id BIGINT NOT NULL,
    status complaintStatus NOT NULL,
    rejectReason VARCHAR(40),
    CONSTRAINT fk_complaint_users FOREIGN KEY (users_id) REFERENCES users(id),
    CONSTRAINT fk_complaint_contractor FOREIGN KEY (contractor_id) REFERENCES contractor(id)
    );