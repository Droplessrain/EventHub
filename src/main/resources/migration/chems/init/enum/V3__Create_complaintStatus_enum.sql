DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'complaint_status') THEN
CREATE TYPE complaint_status AS ENUM ('PENDING', 'ACCEPTED', 'REJECTED');
END IF;
END $$;