CREATE TABLE IF NOT EXISTS complaint (
    id BIGSERIAL PRIMARY KEY,
    dateTime DATE NOT NULL,
    title VARCHAR(40) NOT NULL,
    description VARCHAR(400) NOT NULL,
    usersId BIGINT NOT NULL,
    contractorId BIGINT NOT NULL,
    status ComplaintStatus NOT NULL,
    rejectReason VARCHAR(40),
    CONSTRAINT fk_complaint_users FOREIGN KEY (usersId) REFERENCES users(id),
    CONSTRAINT fk_complaint_contractor FOREIGN KEY (contractorId) REFERENCES contractor(id)
    );