DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'userRole') THEN
CREATE TYPE userRole AS ENUM ('USER', 'ADMIN');
END IF;
END $$;