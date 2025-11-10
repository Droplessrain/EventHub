CREATE TABLE IF NOT EXISTS contractorEvent (
    id BIGSERIAL PRIMARY KEY,
    contractorId BIGINT NOT NULL,
    eventId BIGINT NOT NULL,
    dateTime TIMESTAMP NOT NULL,
    totalTime SMALLINT NOT NULL,
    cost BIGINT NOT NULL,
    serviceType serviceType NOT NULL,
    description VARCHAR(400) NOT NULL,
    CONSTRAINT fk_contractorEvent_contractor FOREIGN KEY (contractorId) REFERENCES contractor(id),
    CONSTRAINT fk_contractorEvent_event FOREIGN KEY (eventId) REFERENCES event(id)
    );