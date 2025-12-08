CREATE TABLE IF NOT EXISTS contractor_event (
    id BIGSERIAL PRIMARY KEY,
    contractor_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    contractor_event_date_time TIMESTAMP NOT NULL,
    total_time SMALLINT NOT NULL CHECK (total_time > 0),
    cost BIGINT NOT NULL CHECK (cost > 0),
    service_type service_type NOT NULL,
    description TEXT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_contractorEvent_contractor FOREIGN KEY (contractor_id) REFERENCES contractor(id),
    CONSTRAINT fk_contractorEvent_event FOREIGN KEY (event_id) REFERENCES event(id)
    );