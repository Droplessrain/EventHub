DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'feedback_status') THEN
CREATE TYPE feedback_status AS ENUM ('PENDING', 'ACCEPTED', 'REJECTED');
END IF;
END $$;