CREATE TABLE IF NOT EXISTS contractorEvent (
    id BIGSERIAL PRIMARY KEY,
    contractor_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    date_time TIMESTAMP NOT NULL,
    total_time SMALLINT NOT NULL,
    cost BIGINT NOT NULL,
    service_type serviceType NOT NULL,
    description VARCHAR(400) NOT NULL,
    CONSTRAINT fk_contractorEvent_contractor FOREIGN KEY (contractor_id) REFERENCES contractor(id),
    CONSTRAINT fk_contractorEvent_event FOREIGN KEY (event_id) REFERENCES event(id)
    );