DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'ComplaintStatus') THEN
CREATE TYPE ComplaintStatus AS ENUM ('PENDING', 'ACCEPTED', 'REJECTED');
END IF;
END $$;