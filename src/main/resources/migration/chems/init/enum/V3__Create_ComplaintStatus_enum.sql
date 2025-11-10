DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'complaintStatus') THEN
CREATE TYPE complaintStatus AS ENUM ('PENDING', 'ACCEPTED', 'REJECTED');
END IF;
END $$;