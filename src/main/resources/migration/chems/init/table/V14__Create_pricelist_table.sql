CREATE TABLE IF NOT EXISTS pricelist (
    id BIGSERIAL PRIMARY KEY,
    contractorId BIGINT NOT NULL,
    serviceType serviceType NOT NULL,
    quantityOfHours SMALLINT NOT NULL,
    price BIGINT NOT NULL,
    experience VARCHAR(400) NOT NULL,
    CONSTRAINT fk_pricelist_contractor FOREIGN KEY (contractorId) REFERENCES contractor(id)
    );