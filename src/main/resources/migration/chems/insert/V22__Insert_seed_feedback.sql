INSERT INTO feedback (date_time, title, description, users_id, contractor_id, status) VALUES
    ('2024-05-01', 'Excellent work!', 'Photographer made wonderful pictures, everything was perfect', 2, 1, 'ACCEPTED'),
    ('2024-05-02', 'Professional', 'Videographer edited video with high quality, recommended', 3, 2, 'PENDING'),
    ('2024-05-03', 'Delicious food', 'Chef prepared exquisite dishes, guests were delighted', 4, 3, 'ACCEPTED')
ON CONFLICT DO NOTHING;