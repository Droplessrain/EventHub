CREATE TABLE IF NOT EXISTS requestBooking (
    id BIGSERIAL PRIMARY KEY,
    dateTime DATE NOT NULL,
    usersId BIGINT NOT NULL,
    contractorId BIGINT NOT NULL,
    eventId BIGINT NOT NULL,
    status bookingStatus NOT NULL,
    rejectReason VARCHAR(40),
    title VARCHAR(40) NOT NULL,
    description VARCHAR(400) NOT NULL,
    CONSTRAINT fk_requestBooking_users FOREIGN KEY (usersId) REFERENCES users(id),
    CONSTRAINT fk_requestBooking_contractor FOREIGN KEY (contractorId) REFERENCES contractor(id),
    CONSTRAINT fk_requestBooking_event FOREIGN KEY (eventId) REFERENCES event(id)
);