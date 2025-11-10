CREATE TABLE IF NOT EXISTS requestBooking (
    id BIGSERIAL PRIMARY KEY,
    date_time TIMESTAMP NOT NULL,
    users_id BIGINT NOT NULL,
    contractor_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    status bookingStatus NOT NULL,
    reject_reason VARCHAR(40),
    title VARCHAR(40) NOT NULL,
    description VARCHAR(400) NOT NULL,
    CONSTRAINT fk_requestBooking_users FOREIGN KEY (users_id) REFERENCES users(id),
    CONSTRAINT fk_requestBooking_contractor FOREIGN KEY (contractor_id) REFERENCES contractor(id),
    CONSTRAINT fk_requestBooking_event FOREIGN KEY (event_id) REFERENCES event(id)
);