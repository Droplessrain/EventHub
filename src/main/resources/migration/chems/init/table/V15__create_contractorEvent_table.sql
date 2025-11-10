CREATE TABLE IF NOT EXISTS contractorEvent (
    id BIGSERIAL PRIMARY KEY,
    contractor_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    date_time TIMESTAMP NOT NULL,
    total_time SMALLINT NOT NULL CHECK (total_time > 0),
    cost BIGINT NOT NULL CHECK (cost > 0),
    type_of_service service_type NOT NULL,
    description TEXT NOT NULL,
    CONSTRAINT fk_contractorEvent_contractor FOREIGN KEY (contractor_id) REFERENCES contractor(id),
    CONSTRAINT fk_contractorEvent_event FOREIGN KEY (event_id) REFERENCES event(id)
    );