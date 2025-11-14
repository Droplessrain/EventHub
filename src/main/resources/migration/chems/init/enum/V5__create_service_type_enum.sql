DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'service_type') THEN
CREATE TYPE service_type AS ENUM ('PHOTOGRAPHER', 'LEAD', 'VIDEOGRAPHER', 'CHEF');
END IF;
END $$;