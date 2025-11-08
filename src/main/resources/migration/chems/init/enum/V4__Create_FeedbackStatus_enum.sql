DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'FeedbackStatus') THEN
CREATE TYPE FeedbackStatus AS ENUM ('PENDING', 'ACCEPTED', 'REJECTED');
END IF;
END $$;