CREATE TABLE IF NOT EXISTS pricelist (
    id BIGSERIAL PRIMARY KEY,
    contractor_id BIGINT NOT NULL,
    service_type SERVICE_TYPE NOT NULL,
    quantity_of_hours SMALLINT NOT NULL CHECK (quantity_of_hours > 0),
    price BIGINT NOT NULL CHECK (price > 0),
    experience VARCHAR(400) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_pricelist_contractor FOREIGN KEY (contractor_id) REFERENCES contractor(id)
    );