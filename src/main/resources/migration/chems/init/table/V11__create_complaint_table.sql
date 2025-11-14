CREATE TABLE IF NOT EXISTS complaint (
    id BIGSERIAL PRIMARY KEY,
    complaint_date_time TIMESTAMP NOT NULL,
    title VARCHAR(80) NOT NULL,
    description TEXT NOT NULL,
    user_id BIGINT NOT NULL,
    contractor_id BIGINT NOT NULL,
    complaint_status complaint_status NOT NULL,
    reject_reason VARCHAR(400),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_complaint_users FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_complaint_contractor FOREIGN KEY (contractor_id) REFERENCES contractor(id)
    );