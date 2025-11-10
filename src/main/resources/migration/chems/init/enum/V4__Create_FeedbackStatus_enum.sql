DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'feedbackStatus') THEN
CREATE TYPE feedbackStatus AS ENUM ('PENDING', 'ACCEPTED', 'REJECTED');
END IF;
END $$;