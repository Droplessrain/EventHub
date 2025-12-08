SELECT 'CREATE DATABASE eventhub'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'eventhub')\gexec