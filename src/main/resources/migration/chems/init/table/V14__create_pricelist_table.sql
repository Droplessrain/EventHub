CREATE TABLE IF NOT EXISTS pricelist (
    id BIGSERIAL PRIMARY KEY,
    contractor_id BIGINT NOT NULL,
    service_type service_type NOT NULL,
    quantity_of_hours SMALLINT NOT NULL CHECK (quantity_of_hours > 0),
    price BIGINT NOT NULL CHECK (price > 0),
    experience TEXT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_pricelist_contractor FOREIGN KEY (contractor_id) REFERENCES contractor(id)
    );