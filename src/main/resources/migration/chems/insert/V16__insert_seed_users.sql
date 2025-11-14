INSERT INTO users (username, password, email, user_role) VALUES
    ('admin', '$2a$10$adminhashedpassword', 'admin@example.com', 'ADMIN'),
    ('john_doe', '$2a$10$userhashedpassword1', 'john@example.com', 'USER'),
    ('jane_smith', '$2a$10$userhashedpassword2', 'jane@example.com', 'USER'),
    ('mike_wilson', '$2a$10$userhashedpassword3', 'mike@example.com', 'USER'),
    ('sarah_brown', '$2a$10$userhashedpassword4', 'sarah@example.com', 'USER')
ON CONFLICT (username) DO NOTHING;