CREATE TABLE IF NOT EXISTS pricelist (
    id BIGSERIAL PRIMARY KEY,
    contractor_id BIGINT NOT NULL,
    service_type SERVICE_TYPE NOT NULL,
    quantity_of_hours SMALLINT NOT NULL,
    price BIGINT NOT NULL,
    experience VARCHAR(400) NOT NULL,
    CONSTRAINT fk_pricelist_contractor FOREIGN KEY (contractor_id) REFERENCES contractor(id)
    );