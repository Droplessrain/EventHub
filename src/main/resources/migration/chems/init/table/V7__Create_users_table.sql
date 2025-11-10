CREATE OR REPLACE FUNCTION check_email_regex(p_email VARCHAR)
RETURNS BOOLEAN AS $$
BEGIN
    IF p_email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$' THEN
        RETURN TRUE;
ELSE
        RETURN FALSE;
END IF;
END;
$$ LANGUAGE plpgsql IMMUTABLE STRICT;

CREATE DOMAIN email AS VARCHAR(255) CHECK (check_email_regex(VALUE));

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    user_email EMAIL NOT NULL,
    role user_role,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);